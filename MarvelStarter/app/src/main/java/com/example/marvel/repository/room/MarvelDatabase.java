package com.example.marvel.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.marvel.repository.entities.Character;
import com.example.marvel.repository.room.dao.CharacterDao;

@Database(entities = { Character.class} , version = 1, exportSchema = false)
public abstract class MarvelDatabase extends RoomDatabase {

    private static MarvelDatabase INSTANCE = null;

    public abstract CharacterDao characterDao();

    public static MarvelDatabase getDatabase(final Context context) {
        if(INSTANCE == null ){
            synchronized (MarvelDatabase.class) {
                if(INSTANCE == null ) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MarvelDatabase.class, "marvel_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
