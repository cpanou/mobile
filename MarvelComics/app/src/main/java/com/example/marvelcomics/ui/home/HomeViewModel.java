package com.example.marvelcomics.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelcomics.repository.ComicRepository;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.repository.gateway.dto.ComicsResponse;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private ComicRepository repository;

    private MutableLiveData<List<Comic>> comicsOnSale;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new ComicRepository(application);
        comicsOnSale = repository.getOnSale();
    }

    public LiveData<List<Comic>> getOnSaleComics(){
        return comicsOnSale;
    }

}