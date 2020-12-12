package com.example.marvelcomics.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marvelcomics.repository.entities.Character;
import com.example.marvelcomics.repository.entities.Comic;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Character character);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Character> character);

    @Query("SELECT * from character_table ORDER BY name ASC")
    DataSource.Factory<Integer, Character> getAllPaged();

    @Query("SELECT * from character_table WHERE name LIKE :name ORDER BY name ASC")
    DataSource.Factory<Integer, Character> searchAllPaged(String name);

    @Query("SELECT * from character_table ")
    LiveData<List<Character>> getAll();

    @Query("SELECT * FROM character_table WHERE id =:id")
    LiveData<Character> getById(int id);

    @Query("DELETE FROM character_table")
    void deleteAll();


}
