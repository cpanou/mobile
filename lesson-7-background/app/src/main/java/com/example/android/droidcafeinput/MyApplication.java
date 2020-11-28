package com.example.android.droidcafeinput;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.android.droidcafeinput.connectivity.InternetConnectionStateChangeReceiver;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(new InternetConnectionStateChangeReceiver(), intentFilter);
    }

}
