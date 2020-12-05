package com.example.roomproject.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomproject.MyApplication;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WordRepository {
    private final WordDao wordDao;
    private final LiveData<List<Word>> wordList;

    public WordRepository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getDatabase(application);
        this.wordDao = database.wordDao();
        this.wordList = this.wordDao.getAllWords();
    }

    public LiveData<List<Word>> getWordList() {
        return wordList;
    }

    public void insert(Word word) {
        MyApplication.EXECUTOR.submit( () -> this.wordDao.insert(word) );
    }

}
