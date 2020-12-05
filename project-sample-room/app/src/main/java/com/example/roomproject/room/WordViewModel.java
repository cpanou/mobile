package com.example.roomproject.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
        words = repository.getWordList();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        repository.insert(word);
    }
}
