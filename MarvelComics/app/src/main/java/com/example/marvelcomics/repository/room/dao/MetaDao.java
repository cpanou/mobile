package com.example.marvelcomics.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marvelcomics.repository.entities.MetaData;

import java.util.List;

@Dao
public interface MetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MetaData metaData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MetaData... metaData);

    @Query("SELECT * from meta_data_table ")
    List<MetaData> getAll();

    @Query("SELECT * FROM meta_data_table WHERE `table` =:table")
    MetaData getByTableName(String table);

    @Query("SELECT * FROM meta_data_table WHERE `table` =:table")
    LiveData<MetaData> getByTableNameLive(String table);

    @Query("DELETE FROM meta_data_table")
    void deleteAll();

}
