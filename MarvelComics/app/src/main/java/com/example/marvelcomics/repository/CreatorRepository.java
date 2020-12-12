package com.example.marvelcomics.repository;

import androidx.lifecycle.LiveData;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.repository.entities.Creator;
import com.example.marvelcomics.repository.gateway.Gateways;
import com.example.marvelcomics.repository.gateway.MarvelGateway;
import com.example.marvelcomics.repository.gateway.dto.CreatorsResponse;
import com.example.marvelcomics.repository.gateway.dto.Result;
import com.example.marvelcomics.repository.room.MarvelDatabase;
import com.example.marvelcomics.repository.room.dao.CreatorDao;
import com.example.marvelcomics.repository.room.dao.MetaDao;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class CreatorRepository {
    private final ExecutorService executor = MyApplication.EXECUTOR_SERVICE;

    private final MarvelGateway gateway;
    private final CreatorDao creators;
    private final MetaDao metaData;


    public CreatorRepository(MyApplication application) {
        gateway = Gateways.get();
        MarvelDatabase database = MarvelDatabase.getDatabase(application);
        creators = database.creators();
        metaData = database.meta();
    }

    public LiveData<List<Creator>> getCreators(int offset, int limit) {
        Result<CreatorsResponse> result = gateway.getCreators(0);
        if (result instanceof Result.Success) {
            CreatorsResponse response = ((Result.Success<CreatorsResponse>) result).get();
        } else {
            String error = ((Result.Error<CreatorsResponse>) result).get().getMessage();
        }
        return creators.getAll();
    }

}
