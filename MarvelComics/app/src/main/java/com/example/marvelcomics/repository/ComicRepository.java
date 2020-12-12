package com.example.marvelcomics.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.repository.entities.MetaData;
import com.example.marvelcomics.repository.gateway.Gateways;
import com.example.marvelcomics.repository.gateway.MarvelGateway;
import com.example.marvelcomics.repository.gateway.dto.ComicsResponse;
import com.example.marvelcomics.repository.gateway.dto.Result;
import com.example.marvelcomics.repository.room.MarvelDatabase;
import com.example.marvelcomics.repository.room.dao.ComicDao;
import com.example.marvelcomics.repository.room.dao.MetaDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class ComicRepository {
    private final String TAG = ComicRepository.class.getSimpleName();
    private final ExecutorService executor = MyApplication.EXECUTOR_SERVICE;

    private final MarvelGateway gateway;
    private final ComicDao comics;

    private final MetaDao metaDataDao;

    private MetaData metaData;

    public ComicRepository(Context context) {
        gateway = Gateways.get();
        MarvelDatabase database = MarvelDatabase.getDatabase(context);
        comics = database.comics();
        metaDataDao = database.meta();
        metaDataDao.getByTableNameLive(Comic.META_TAG).observeForever((meta) -> metaData = meta);
    }

    public DataSource.Factory<Integer, Comic> getComics() {
        return comics.getAllPaged();
    }

    public MutableLiveData<List<Comic>> getOnSale(){
        MutableLiveData<List<Comic>> results = new MutableLiveData<>();
        executor.submit(() -> {
            Result<ComicsResponse> result = gateway.getComicsOnSale();
            if(result instanceof Result.Success){
                ComicsResponse response = ((Result.Success<ComicsResponse>) result).get();
                results.postValue(response.getData().getResults());
            } else {
                results.postValue(null);
            }
        });
        return results;
    }
    public void getOnSale(Gateways.MarvelCallback<ComicsResponse> callback){
        gateway.getComicsOnSale(callback);
    }

    public DataSource.Factory<Integer, Comic> searchComics(String query) {
        executor.submit(() -> downloadAndStore(query));
        return comics.searchAllPaged("%" + query + "%");
    }

    public boolean queryUpdate() {
        if (metaData != null && metaData.getTotal() > metaData.getOffset()) {
            executor.submit(() -> {
                downloadAndStore(metaData.getOffset());
            });
            return true;
        } else if (metaData == null) {
            executor.submit(() -> {
                if (metaDataDao.getByTableName(Comic.META_TAG).getOffset() == 0) {
                    downloadAndStore(0);
                }
            });
            return true;
        }
        return false;
    }

    private void downloadAndStore(String query) {
        Result<ComicsResponse> result = gateway.getComics(query);
        storeResult(result);
    }

    private void downloadAndStore(int offset) {
        Result<ComicsResponse> result = gateway.getComics(offset);
        storeResult(result);
    }

    private void storeResult(Result<ComicsResponse> result) {
        if (result instanceof Result.Success) {
            ComicsResponse response = ((Result.Success<ComicsResponse>) result).get();
            List<Comic> ComicList = response.getData().getResults().stream()
                    .peek(this::mapComic)
                    .collect(Collectors.toList());
            comics.insertAll(ComicList);
            MetaData meta = MetaData.from(response.getData());
            Log.d(TAG, "Updating Meta Comics: " + meta.getOffset());
            metaDataDao.insert(meta);
        } else {
            String error = ((Result.Error<ComicsResponse>) result).get().getMessage();
            Log.d(TAG, "ERROR FETCHING ComicS" + error);
            comics.insertAll(new ArrayList<>());
        }
    }

    private void mapComic(Comic comic) {
        comic.setThumbnailUrl(comic.getThumbnail().url());
        comic.setCharactersUrl(comic.getCharacters().getCollectionURI());
        comic.setCreatorsUrl(comic.getCreators().getCollectionURI());
        comic.setStoriesUrl(comic.getStories().getCollectionURI());
        comic.setSeriesUrl(comic.getSeries().getCollectionURI());
        comic.setPrice(comic.getPrices().size());
    }

    public void clear() {
        executor.submit(() -> {
            comics.deleteAll();
            metaDataDao.deleteAll();
        });
    }
}
