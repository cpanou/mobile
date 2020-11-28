package com.example.android.droidcafeinput.marvel;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.droidcafeinput.R;
import com.example.android.droidcafeinput.marvel.dto.MarvelResponse;
import com.example.android.droidcafeinput.marvel.dto.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
    public interface MarvelCallback {
        void onResponse(MarvelResponse marvelResponse);
    }

    /**
     * Sample Response for a single comic:
     * {
     *     "code": 200,
     *     "status": "Ok",
     *     "copyright": "© 2020 MARVEL",
     *     "attributionText": "Data provided by Marvel. © 2020 MARVEL",
     *     "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2020 MARVEL</a>",
     *     "etag": "f1cbaa3da92bc6c78a878e6a61ed4f23fde4c33d",
     *     "data": {
     *         "offset": 0,
     *         "limit": 20,
     *         "total": 1,
     *         "count": 1,
     *         "results": [
     *             {
     *                 "id": 1749,
     *                 "digitalId": 0,
     *                 "title": "Official Handbook of the Marvel Universe (2004) #11 (X-MEN - AGE OF APOCALYPSE)",
     *                 "issueNumber": 11,
     *                 "variantDescription": "X-MEN - AGE OF APOCALYPSE",
     *                 "description": "Your complete guide to the epic saga! This Official Handbook includes in-depth bios on more than 40 denizens of the Age of Apocalypse - from Abyss to Weapon X! Plus: An all-new cover by superstar-in-waiting Mark Brooks, digitally painted by Justin Ponsor.\r<br>48 PGS./Marvel PSR ...$3.99\r<br>",
     *                 "modified": "-0001-11-30T00:00:00-0500",
     *                 "textObjects": [
     *                     {
     *                         "type": "issue_solicit_text",
     *                         "language": "en-us",
     *                         "text": "Your complete guide to the epic saga! This Official Handbook includes in-depth bios on more than 40 denizens of the Age of Apocalypse - from Abyss to Weapon X! Plus: An all-new cover by superstar-in-waiting Mark Brooks, digitally painted by Justin Ponsor.\r<br>48 PGS./Marvel PSR ...$3.99\r<br>"
     *                     }
     *                 ],
     *          }
     *       ]
     *  }
     * }
     */
    public void getComic(final MarvelCallback callback) {
        // (1) - make an async Http call with the OkHttpClient
        //    URL : http://gateway.marvel.com/v1/public/comics/1749
        //    Method: GET
        //
        // (2) - enqueue the call and provide the onFailure and onResponse Callbacks
        // (3) - the single Comic endpoint returns the same object as the getComics endpoint
        // so you can parse it the same way as the sample endpoint bellow( @getComics )
        // (4) - Then call the onResponse method from the Provided Callback
        // to return the results to the fragment
    }


    public void getComics(final MarvelCallback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();

        //(NOTE) - if you want to use your own Private and Public keys to call the Marvel API use the
        // Util getAuthWithMD5(key, key) method to construct the required authentication string and append
        // it to the url.
        String authentication = Util.getAuthWithMD5();

        //(ALT) - Use the pre-constructed String.
        //String authentication = "?ts=1605656906&apikey=f4f60bbba0eb44b0a8ae7b22b70eada6&hash=2160259a947a668a2ad6db817b4f6e8c";
        String url = "http://gateway.marvel.com/v1/public/comics?" + authentication;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        // (1) - Make an Async call with okHttp. OkHttp will create a worker thread to execute the http call.
        // The onFailure and onResponse callbacks are executed on the worker thread.
        // To acquire the data and update the UI in an activity/fragment we need to pass any data to the main thread.
        // To achieve that we use a Handler from the Main thread's Looper, see @respond()
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
                    final MarvelResponse marvelResponse = mapper.readValue(response.body().string(), MarvelResponse.class);
                    Log.d(TAG, "SUCCESS response:" + marvelResponse.getAttributionText());
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
    private void respond(final MarvelResponse marvelResponse, final MarvelCallback callback) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(marvelResponse);
            }
        });
    }

}
