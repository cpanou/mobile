package com.example.marvelcomics.repository.gateway.service;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.marvelcomics.repository.gateway.Gateways;
import com.example.marvelcomics.repository.gateway.dto.ErrorResponse;
import com.example.marvelcomics.repository.gateway.dto.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class MarvelService {
    private final static String TAG = MarvelService.class.getSimpleName();

    private static final String MARVEL_URL = "http://gateway.marvel.com/v1/public";

    private final ObjectMapper mapper;
    private final Handler mainHandler;

    protected MarvelService() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mainHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * HTTP Handling Methods
     */
    protected <T> Result<T> requestResource(String uri, Class<T> tClass) {
        Call call = getCall(uri+ "?"  + MarvelAuthentication.getAuthWithMD5());
        return execute(tClass, call);
    }

    protected <T> Result<T> request(String resourceUrl, final Class<T> tClass) {
        String path =  resourceUrl + "&" + MarvelAuthentication.getAuthWithMD5();
        Log.d(TAG, "invoking:" + path);
        Call call = getCall(MARVEL_URL + path);
        return execute( tClass, call);
    }

    protected <T> Result<T> request(String resourceUrl, final Class<T> tClass, int offset) {
        String path = resourceUrl + "&" + MarvelAuthentication.getAuthWithMD5();
        Log.d(TAG, "invoking:" + path);
        Call call = getCall(MARVEL_URL + path);
        return execute( tClass, call);
    }

    @NotNull
    private <T> Result<T> execute(Class<T> tClass, Call call) {
        try (Response response = call.execute()) {
            Log.d(TAG, response.code() + " " + response.message());
            return parseResult(response, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorResponse errorResponse = new ErrorResponse("100", e.getLocalizedMessage());
            return new Result.Error<>(errorResponse);
        }
    }


    protected <T> void request(String resourceUrl, final Gateways.MarvelCallback<T> callback, final Class<T> tClass) {
        String path = resourceUrl + "&" + MarvelAuthentication.getAuthWithMD5();
        Call call = getCall(MARVEL_URL + path);
        executeAsync(callback, tClass, call);
    }

    private <T> void executeAsync(Gateways.MarvelCallback<T> callback, Class<T> tClass, Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, e.getLocalizedMessage());
                ErrorResponse errorResponse = new ErrorResponse("100", e.getLocalizedMessage());
                respond(callback, new Result.Error<>(errorResponse));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, response.code() + " " + response.message());
                Result<T> result = parseResult(response, tClass);
                respond(callback, result);
            }

        });
    }

    private <T> void respond(final Gateways.MarvelCallback<T> callback, final Result<T> result) {
        mainHandler.post(() -> callback.onComplete(result));
    }

    /**
     * Utilities
     */
    private Call getCall(String resourceUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(resourceUrl)
                .build();
        return okHttpClient.newCall(request);
    }

    @NotNull
    private <T> Result<T> parseResult(@NotNull Response response, Class<T> tClass) throws IOException {
        Result<T> result;
        if (response.code() % 500 < 100) {
            Log.d(TAG, response.code() + " " + response.message());
            ErrorResponse errorResponse = new ErrorResponse(String.valueOf(response.code()), response.message());
            result = new Result.Error<>(errorResponse);
        } else if (response.code() % 400 < 100) {
            ErrorResponse errorResponse = mapper.readValue(Objects.requireNonNull(response.body()).string(), ErrorResponse.class);
            Log.d(TAG, errorResponse.getCode() + " " + errorResponse.getMessage());
            result = new Result.Error<>(errorResponse);
        } else {
            T body = mapper.readValue(Objects.requireNonNull(response.body()).string(), tClass);
            result = new Result.Success<>(body);
        }
        response.close();
        return result;
    }


    /*
    public abstract <T> Result<T> fetchAll(int offset);
    public abstract <T> Result<T> fetchById(int identifier);
    public abstract <T> Result<T> fetchAllAsync(int offset, final Gateways.MarvelCallback<T> callback);
    public abstract <T> Result<T> fetchByIdAsync(int identifier, final Gateways.MarvelCallback<T> callback);
     */
}
