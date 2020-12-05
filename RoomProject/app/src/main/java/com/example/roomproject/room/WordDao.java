package com.example.roomproject.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    //Example
    @Query("SELECT * FROM word_table WHERE word =:word AND word=:att2 ORDER BY word ASC")
    Word getWord(String word, String att2);

    @Query("DELETE FROM word_table WHERE word=:word")
    void deleteWord(String word);

    @Query("DELETE FROM word_table")
    void deleteAll();

}
