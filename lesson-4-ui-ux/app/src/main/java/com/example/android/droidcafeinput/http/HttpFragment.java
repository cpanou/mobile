package com.example.android.droidcafeinput.http;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.droidcafeinput.R;
import com.example.android.droidcafeinput.http.dto.ErrorResp;
import com.example.android.droidcafeinput.http.dto.Marver;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpFragment extends Fragment {
    private final String TAG = HttpFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_http, container, false);
        Log.d(HttpFragment.class.getSimpleName(), "CREATED");

        Button button = root.findViewById(R.id.http_button);

        //Maps POJOs to json and vice versa
        final ObjectMapper mapper = new ObjectMapper();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                String ts  = String.valueOf(System.currentTimeMillis());
                String hash = "";
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update((ts+"f4f60bbba0eb44b0a8ae7b22b70eada6"+"4f0a1bf779e40df51c8009217fffc104330e1ff7").getBytes());
                    hash = new String(md.digest());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                String url = "http://gateway.marvel.com/v1/public/comics?" +
                        "ts=1605656906&apikey=f4f60bbba0eb44b0a8ae7b22b70eada6&hash=2160259a947a668a2ad6db817b4f6e8c";
                Log.d(TAG, hash);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);

                //Sync call: executed on the thread that invoked it
//                try {
//                    Response response = call.execute();
//                    String body = response.body().toString();
//                    Log.d(TAG, body);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                //Async call by okHttp
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d( TAG, e.getLocalizedMessage() );
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d(TAG, response.code() + " " + response.message());
                        if( response.code() % 500 < 100)
                            Log.d(TAG, "500 SERVER ERROR");
                        else if ( response.code() % 400 < 100) {
                            ErrorResp pojo = mapper.readValue(response.body().string(), ErrorResp.class);
                            Log.d(TAG, "400 CLIENT ERROR " + pojo );
                        } else {
                            Marver pojo = mapper.readValue(response.body().string(), Marver.class);
//                            String responseBody = response.body().string();
                            Log.d(TAG, "SUCCESS response:" + pojo.getAttributionText());
                        }
                        response.close();
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(HttpFragment.class.getSimpleName(), "DESTROYED");
    }

}