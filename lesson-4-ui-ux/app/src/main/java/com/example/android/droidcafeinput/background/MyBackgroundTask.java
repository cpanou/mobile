package com.example.android.droidcafeinput.background;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyBackgroundTask extends AsyncTask<Void, Void, String> {
    private final WeakReference<TextView> taskTextView;
    int sleepTime = 1;

    public MyBackgroundTask(TextView taskTextView) {
        super();
        this.taskTextView = new WeakReference<>(taskTextView);
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
    }

    //Worker Thread
    @Override
    protected String doInBackground(Void... voids) {

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("MyBackgroundTask", " FINISHED");
        return "Awake at last after sleeping for " + sleepTime + " milliseconds!";
    }

    //UI Thread
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    //UI Thread
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        taskTextView.get().setText(result);
    }

}
