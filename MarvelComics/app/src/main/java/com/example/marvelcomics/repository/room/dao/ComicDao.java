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
public interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Comic comic);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Comic> comic);

    @Query("SELECT * from comic_table ORDER BY title ASC")
    DataSource.Factory<Integer, Comic> getAllPaged();

    @Query("SELECT * from comic_table WHERE title LIKE :title ORDER BY title ASC")
    DataSource.Factory<Integer, Comic> searchAllPaged(String title);

    @Query("SELECT * from comic_table ")
    LiveData<List<Comic>> getAll();

    @Query("SELECT * FROM comic_table WHERE id =:id")
    LiveData<Comic> getById(int id);

    @Query("DELETE FROM comic_table")
    void deleteAll();

}
