package com.example.marvelcomics.repository.gateway;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.marvelcomics.repository.gateway.dto.Image;
import com.example.marvelcomics.repository.gateway.images.ImageSize;
import com.example.marvelcomics.repository.gateway.images.ImageVersion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ImageGateway {
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final Map<String, Bitmap> imageMap = new HashMap<>();

    public static Fetcher with(View view, String url) {
        return new Fetcher(view, breakDown(url));
    }

    public static Fetcher with(View view, Image image) {
        return new Fetcher(view, new String[]{ image.getPath(), "."+image.getExtension()});
    }

    private static String[] breakDown(String url){
        int indexOfDot = url.lastIndexOf(".");
        String path = url.substring(0,(indexOfDot));
        String extension = url.substring(indexOfDot);
        return new String[] { path, extension };
    }

    public static class Fetcher {
        private final View holder;
        private final String[] parts;
        public Fetcher(View view, String[] parts){
            this.holder = view;
            this.parts = parts;
        }
        public RequestBuilder<Drawable> draw(ImageVersion version, ImageSize size) {
            String finalUrl = parts[0] + "/" + version.apply(size) + parts[1];
            return Glide.with(holder).load(finalUrl);
        }
    }

}
