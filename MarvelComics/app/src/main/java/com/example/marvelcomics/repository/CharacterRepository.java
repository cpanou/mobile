package com.example.marvelcomics.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Character;
import com.example.marvelcomics.repository.entities.MetaData;
import com.example.marvelcomics.repository.gateway.Gateways;
import com.example.marvelcomics.repository.gateway.MarvelGateway;
import com.example.marvelcomics.repository.gateway.dto.CharactersResponse;
import com.example.marvelcomics.repository.gateway.dto.Result;
import com.example.marvelcomics.repository.room.MarvelDatabase;
import com.example.marvelcomics.repository.room.dao.CharacterDao;
import com.example.marvelcomics.repository.room.dao.MetaDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharacterRepository {
    private final String TAG = CharacterRepository.class.getSimpleName();
    private final ExecutorService executor = MyApplication.EXECUTOR_SERVICE;

    private final MarvelGateway gateway;
    private final CharacterDao characters;

    private final MetaDao metaDataDao;

    private MetaData metaData;

    public CharacterRepository(Context context) {
        gateway = Gateways.get();
        MarvelDatabase database = MarvelDatabase.getDatabase(context);
        characters = database.characters();
        metaDataDao = database.meta();
        metaDataDao.getByTableNameLive(Character.META_TAG).observeForever((meta) -> metaData = meta);
    }

    public DataSource.Factory<Integer, Character> getCharacters() {
        return characters.getAllPaged();
    }

    public DataSource.Factory<Integer, Character> searchCharacters(String query) {
        executor.submit(() -> downloadAndStoreSearch(query));
        return characters.searchAllPaged("%" + query + "%");
    }

    public boolean queryUpdate() {
        if (metaData != null && metaData.getTotal() > metaData.getOffset()) {
            executor.submit(() -> {
                downloadAndStore(metaData.getOffset());
            });
            return true;
        } else if (metaData == null) {
            executor.submit(() -> {
                if (metaDataDao.getByTableName(Character.META_TAG).getOffset() == 0) {
                    downloadAndStore(0);
                }
            });
            return true;
        }
        return false;
    }


    private void downloadAndStoreSearch(String query) {
        Result<CharactersResponse> result = gateway.getCharacters(query);
        storeResult(result);
    }

    private void downloadAndStore(int offset) {
        Result<CharactersResponse> result = gateway.getCharacters(offset);
        storeResult(result);
    }

    private void storeResult(Result<CharactersResponse> result) {
        if (result instanceof Result.Success) {
            CharactersResponse response = ((Result.Success<CharactersResponse>) result).get();
            List<Character> characterList = response.getData().getResults().stream()
                    .peek(this::mapCharacter)
                    .collect(Collectors.toList());
            characters.insertAll(characterList);
            MetaData meta = MetaData.from(response.getData());
            Log.d(TAG, "Updating Meta Characters: " + meta.getOffset());
            metaDataDao.insert(meta);
        } else {
            String error = ((Result.Error<CharactersResponse>) result).get().getMessage();
            Log.d(TAG, "ERROR FETCHING CHARACTERS" + error);
            characters.insertAll(new ArrayList<>());
        }
    }

    private void mapCharacter(Character character) {
        character.setThumbnailUrl(character.getThumbnail().url());
        character.setComics_available(character.getComics().getAvailable());
        character.setSeries_available(character.getSeries().getAvailable());
        character.setStories_available(character.getStories().getAvailable());
    }

    public void clear() {
        executor.submit(() -> {
            characters.deleteAll();
            metaDataDao.deleteAll();
        });
    }
}
