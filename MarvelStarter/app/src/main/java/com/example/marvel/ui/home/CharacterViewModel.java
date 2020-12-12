package com.example.marvel.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvel.repository.MarvelRepository;
import com.example.marvel.repository.entities.Character;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private final LiveData<List<Character>> characterLiveData;
    private final MarvelRepository repository;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        repository = new MarvelRepository(application);
        characterLiveData = repository.getCharacters();
    }


    public LiveData<List<Character>> getCharacterLiveData() {
        return characterLiveData;
    }

    public void updateCharacters() {
        repository.updateCharacters(0);
    }

}
