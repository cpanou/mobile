package com.example.android.droidcafeinput.background;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class MyBackgroundLoader extends AsyncTaskLoader<String> {
    public static final int ID = 123;

    public MyBackgroundLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        int sleepTime = 2000;
        int chunks = sleepTime/200;
        for(int i = 1; i <= 200; i++) {
            try {
                Thread.sleep(chunks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("MyBackgroundTask", " FINISHED");
        return "Awake at last after sleeping for " + sleepTime + " milliseconds!";
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        this.forceLoad();
    }

}
