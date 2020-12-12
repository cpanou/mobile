package com.example.marvel.repository.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marvel.repository.entities.Character;

import java.util.List;

@Dao
public interface CharacterDao {

    //Fetches all characters from Db to UI components
    @Query("SELECT * FROM characters_table")
    LiveData<List<Character>> getCharacters();

    //Stores Marvel characters on Response from marvel Service
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Character> characters);

}
