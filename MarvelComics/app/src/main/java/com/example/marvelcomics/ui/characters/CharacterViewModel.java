package com.example.marvelcomics.ui.characters;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.repository.CharacterRepository;
import com.example.marvelcomics.repository.entities.Character;
import com.example.marvelcomics.repository.entities.MetaData;

public class CharacterViewModel extends AndroidViewModel {
    private final LiveData<PagedList<Character>> characters;
    private final CharacterRepository repository;

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public CharacterViewModel(Application context) {
        super(context);
        repository = new CharacterRepository(context);
        characters = new LivePagedListBuilder<>(
                repository.getCharacters(),
                100)
                .setBoundaryCallback(new PagedList.BoundaryCallback<Character>() {
                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                        queryUpdate();
                    }
                })
                .build();
    }

    public LiveData<PagedList<Character>> getCharacters() {
        return characters;
    }

    public boolean queryUpdate() {
        return repository.queryUpdate();
    }

}