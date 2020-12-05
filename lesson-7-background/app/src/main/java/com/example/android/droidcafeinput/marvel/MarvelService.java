package com.example.android.droidcafeinput.marvel;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.droidcafeinput.marvel.dto.CharactersResponse;
import com.example.android.droidcafeinput.marvel.dto.ComicsResponse;
import com.example.android.droidcafeinput.marvel.dto.CreatorsResponse;
import com.example.android.droidcafeinput.marvel.dto.ErrorResponse;
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

public class MarvelService extends Service {
    private final static String TAG = MarvelService.class.getSimpleName();
    private ObjectMapper mapper;
    private Handler mainHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MarvelBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // The Handler Constructor requires a Threads Looper to create an instance.
        // the returned instance is tied to the provided looper, meaning that the handler's
        // messages and runnables will be posted to that threads queue
        mainHandler = new Handler(getMainLooper());
    }

    public class MarvelBinder extends Binder {
        MarvelService getService() {
            return MarvelService.this;
        }
    }

    // An Interface so that calling classes can provide callbacks to communicate with the service methods
    public interface MarvelCallback<T> {
        void onResponse(T marvelResponse);
    }


    /**
     * PUBLIC METHODS FOR CLIENTS
     * @param callback
     */
    private final String BASE_URL = "http://gateway.marvel.com";

    public void getCreator(String id, final MarvelCallback<CreatorsResponse> callback) {
        request(callback, CreatorsResponse.class, BASE_URL +"/v1/public/creators/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getCreators(final MarvelCallback<CreatorsResponse> callback) {
        request(callback, CreatorsResponse.class,BASE_URL +"/v1/public/creators?" + Util.getAuthWithMD5());
    }

    public void getCharacter(String id, final MarvelCallback<CharactersResponse> callback) {
        request(callback, CharactersResponse.class, BASE_URL + "/v1/public/characters/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getCharacters(final MarvelCallback<CharactersResponse> callback) {
        request(callback, CharactersResponse.class,BASE_URL + "/v1/public/characters?" + Util.getAuthWithMD5());
    }

    //1749
    public void getComic(String id, final MarvelCallback<ComicsResponse> callback) {
        request(callback, ComicsResponse.class, BASE_URL + "/v1/public/comics/" + id + "?" + Util.getAuthWithMD5());
    }

    public void getComics( final MarvelCallback<ComicsResponse> callback) {
        request(callback, ComicsResponse.class, BASE_URL + "/v1/public/comics?" + Util.getAuthWithMD5());
    }

//
//    public void getEmployees( final MarvelCallback<EmployeesResponse> callback) {
//        request(callback, EmployeesResponse.class, BASE_URL + "/v1/public/comics?" + Util.getAuthWithMD5());
//    }


    /**
     * HTTP METHODS
     * @param callback
     * @param url
     */
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
                if (response.code() % 500 < 100) {
                    Log.d(TAG, response.code() + " " + response.message());
                    respond(null, callback);
                } else if (response.code() % 400 < 100) {
                    ErrorResponse error = mapper.readValue(response.body().string(), ErrorResponse.class);
                    Log.d(TAG, response.code() + " " + response.message() + " " + error);
                    respond(null, callback);
                } else {
                    final T marvelResponse = mapper.readValue(response.body().string(), tClass);
                    respond(marvelResponse, callback);
                }
                response.close();
            }
        });
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
