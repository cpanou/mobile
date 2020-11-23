package com.example.android.droidcafeinput.background;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyBackgroundTask extends AsyncTask<Void, Double, String> {
    private final WeakReference<TextView> taskTextView;
    private final WeakReference<ProgressBar> progressBar;
    int sleepTime = 1;

    public MyBackgroundTask(TextView taskTextView, ProgressBar progressBar) {
        super();
        this.taskTextView = new WeakReference<>(taskTextView);
        this.progressBar = new WeakReference<>(progressBar);
    }

    //UI Thread
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Random r = new Random();
        int n = r.nextInt(11);
        this.sleepTime = 10000;
        String showText = "Will nap for "+ this.sleepTime +" millis";
        this.taskTextView.get().setText(showText);
        this.progressBar.get().setVisibility(View.VISIBLE);
    }

    //Worker Thread
    @Override
    protected String doInBackground(Void... voids) {
        int chunks = sleepTime/200;
        for(int i = 1; i <= 200; i++) {
            try {
                publishProgress(((double)(i * chunks)) / sleepTime );
                Thread.sleep(chunks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("MyBackgroundTask", " FINISHED");
        return "Awake at last after sleeping for " + sleepTime + " milliseconds!";
    }

    //UI Thread
    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        this.progressBar.get().setProgress((int)(values[0]*100));
    }

    //UI Thread
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        taskTextView.get().setText(result);
        this.progressBar.get().setVisibility(View.INVISIBLE);
    }

}
