package com.example.marvelcomics.repository.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.repository.entities.Character;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.repository.entities.Creator;
import com.example.marvelcomics.repository.entities.MetaData;
import com.example.marvelcomics.repository.room.dao.CharacterDao;
import com.example.marvelcomics.repository.room.dao.ComicDao;
import com.example.marvelcomics.repository.room.dao.CreatorDao;
import com.example.marvelcomics.repository.room.dao.MetaDao;

@Database(entities = { Character.class, Comic.class, Creator.class, MetaData.class }, version = 1, exportSchema = false)
public abstract class MarvelDatabase extends RoomDatabase {

    private static MarvelDatabase INSTANCE;

    public abstract MetaDao meta();

    public abstract CharacterDao characters();

    public abstract ComicDao comics();

    public abstract CreatorDao creators();

    private static final Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            MyApplication.EXECUTOR_SERVICE.submit(() -> {
                INSTANCE.meta().insert(MetaData.CHARACTERS_INITIAL);
                INSTANCE.meta().insert(MetaData.COMICS_INITIAL);
                INSTANCE.meta().insert(MetaData.CREATORS_INITIAL);
            });
        }
    };

    public static MarvelDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MarvelDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MarvelDatabase.class, "marvel_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
