package com.example.roomproject.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomproject.MyApplication;


@Database( entities = { Word.class }, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase instance;

    public static WordRoomDatabase getDatabase(Context context) {
        if(instance == null){
            //initialize instance with a new database
            synchronized (WordRoomDatabase.class) {
                if(instance == null)
                    instance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database" )
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
            }
        }
        return instance;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    String[] words = {"dolphin", "crocodile", "cobra"};
                    MyApplication.EXECUTOR.submit(()->{
                        // Start the app with a clean database every time.
                        // Not needed if you only populate the database
                        // when it is first created
                        instance.wordDao().deleteAll();

                        for (int i = 0; i <= words.length - 1; i++) {
                            Word word = new Word(words[i]);
                            instance.wordDao().insert(word);
                        }
                        return null;
                    });
                }
            };

}
