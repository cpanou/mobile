package com.example.marvelcomics.ui.comics;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.marvelcomics.repository.ComicRepository;
import com.example.marvelcomics.repository.entities.Comic;

public class ComicsViewModel extends AndroidViewModel {
    private final LiveData<PagedList<Comic>> characters;
    private final ComicRepository repository;

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public ComicsViewModel(Application context) {
        super(context);
        repository = new ComicRepository(context);
        characters = new LivePagedListBuilder<>(
                repository.getComics(),
                100)
                .setBoundaryCallback(new PagedList.BoundaryCallback<Comic>() {
                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                        queryUpdate();
                    }
                })
                .build();
    }

    public LiveData<PagedList<Comic>> getComics() {
        return characters;
    }

    public boolean queryUpdate() {
        return repository.queryUpdate();
    }

}