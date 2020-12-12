package com.example.marvelcomics.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marvelcomics.repository.entities.Creator;

import java.util.List;

@Dao
public interface CreatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Creator creator);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Creator... creator);

    @Query("SELECT * from creator_table ")
    LiveData<List<Creator>> getAll();

    @Query("SELECT * FROM creator_table WHERE id =:id")
    LiveData<Creator> getById(int id);

    @Query("DELETE FROM creator_table")
    void deleteAll();

}
