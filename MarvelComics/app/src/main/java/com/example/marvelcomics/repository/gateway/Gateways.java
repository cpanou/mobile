package com.example.marvelcomics.repository.gateway;

import android.graphics.Bitmap;

import com.example.marvelcomics.repository.gateway.dto.Result;

public class Gateways {

    public interface MarvelCallback<T> {
        void onComplete(Result<T> comics);
    }

    public interface ImageCallback {
        void onComplete(Bitmap bitmap);
    }

    public static MarvelGateway get() {
        return new MarvelGateway();
    }


}
