package com.example.marvel.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marvel.repository.entities.Character;
import com.example.marvel.repository.gateway.MarvelService;
import com.example.marvel.repository.gateway.dto.CharactersResponse;
import com.example.marvel.repository.room.MarvelDatabase;
import com.example.marvel.repository.room.dao.CharacterDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MarvelRepository {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        private final MarvelService gateway;
        private final CharacterDao characters;

        public MarvelRepository(Context context){
            gateway = MarvelService.get();
            MarvelDatabase database = MarvelDatabase.getDatabase(context);
            characters = database.characterDao();
        }

        public LiveData<List<Character>> getCharacters() {
            return characters.getCharacters();
        }

        public void updateCharacters(int offset) {
            executorService.submit(() -> {
                CharactersResponse response = gateway.getCharacters(offset);
                if( response != null) {
                    List<Character> charactersList = response.getData().getResults().stream()
                            .peek(character -> character.setThumbnail_url(character.getThumbnail().url()))
                            .collect(Collectors.toList());
                    characters.insertAll(charactersList);
                } else {
                    characters.insertAll(new ArrayList<>());
                }
            });
        }



}
