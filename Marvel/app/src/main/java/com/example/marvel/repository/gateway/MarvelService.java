package com.example.marvel.repository.gateway;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.marvel.repository.gateway.dto.CharactersResponse;
import com.example.marvel.repository.gateway.dto.ComicsResponse;
import com.example.marvel.repository.gateway.dto.CreatorsResponse;
import com.example.marvel.repository.gateway.dto.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MarvelService {
    private final static String TAG = MarvelService.class.getSimpleName();

    private static MarvelService instance;
    private final ObjectMapper mapper;
    private final Handler mainHandler;

    private MarvelService() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // The Handler Constructor requires a Threads Looper to create an instance.
        // the returned instance is tied to the provided looper, meaning that the handler's
        // messages and runnables will be posted to that threads queue
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static MarvelService get() {
        if( instance == null)
            instance = new MarvelService();
        return instance;
    }


    /**
     * PUBLIC METHODS FOR CLIENTS ASYNC
     *
     */

    public CharactersResponse getCharacters(int offset) {
        return request(BASE_URL + "/v1/public/characters?offset=" + offset + "&orderBy=name&limit=100&"+Util.getAuthWithMD5(), CharactersResponse.class);
    }

    /**
     * PUBLIC METHODS FOR CLIENTS ASYNC
     *
     * @param callback
     */
    private final String BASE_URL = "http://gateway.marvel.com";

    public void getCreator(String id, final MarvelCallback<CreatorsResponse> callback) {
        request(callback, CreatorsResponse.class, BASE_URL + "/v1/public/creators/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getCreators(final MarvelCallback<CreatorsResponse> callback) {
        request(callback, CreatorsResponse.class, BASE_URL + "/v1/public/creators?" + Util.getAuthWithMD5());
    }

    public void getCharacter(String id, final MarvelCallback<CharactersResponse> callback) {
        request(callback, CharactersResponse.class, BASE_URL + "/v1/public/characters/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getCharacters(final MarvelCallback<CharactersResponse> callback) {
        request(callback, CharactersResponse.class, BASE_URL + "/v1/public/characters?" + Util.getAuthWithMD5());
    }

    //1749
    public void getComic(String id, final MarvelCallback<ComicsResponse> callback) {
        request(callback, ComicsResponse.class, BASE_URL + "/v1/public/comics/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getComics(final MarvelCallback<ComicsResponse> callback) {
        request(callback, ComicsResponse.class, BASE_URL + "/v1/public/comics?" + Util.getAuthWithMD5());
    }

//
//    public void getEmployees( final MarvelCallback<EmployeesResponse> callback) {
//        request(callback, EmployeesResponse.class, BASE_URL + "/v1/public/comics?" + Util.getAuthWithMD5());
//    }


    /**
     * HTTP METHODS
     * @param url
     */
    private <T> T request(String url, final Class<T> tClass) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            Log.d(TAG, response.code() + " " + response.message());
            return parseResult(response, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // An Interface so that calling classes can provide callbacks to communicate with the service methods
    public interface MarvelCallback<T> {
        void onResponse(T marvelResponse);
    }

    private <T> void request(final MarvelCallback<T> callback, final Class<T> tClass, String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, e.getLocalizedMessage());
                respond(null, callback);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                T result = parseResult(response, tClass);
                respond(result, callback);
            }

        });
    }

    private <T> T parseResult(@NotNull Response response, Class<T> tClass) throws IOException {
        T result;
        if (response.code() % 500 < 100) {
            Log.d(TAG, response.code() + " " + response.message());
            result = null;
        } else if (response.code() % 400 < 100) {
            ErrorResponse error = mapper.readValue(response.body().string(), ErrorResponse.class);
            Log.d(TAG, response.code() + " " + response.message() + " " + error);
            result = null;
        } else {
            result = mapper.readValue(response.body().string(), tClass);
        }
        response.close();
        return result;
    }

    // (2) - The respond method uses the Handler to post a runnable to the main thread's queue.
    // in this way we can post messages and runnable tasks from any worker thread.
    // the runnable will be executed in time on the UI thread so any UI manipulation can be placed here,
    // or passed down from the calling activity in the form of a callback ( as in our example: callback )
    // (3) - See the onCreate method where we acquire the Handler instance.
    private <T> void respond(final T marvelResponse, final MarvelCallback<T> callback) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(marvelResponse);
            }
        });
    }

}
