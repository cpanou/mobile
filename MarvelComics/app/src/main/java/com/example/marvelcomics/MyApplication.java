package com.example.marvelcomics;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.marvelcomics.receivers.NetworkReceiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(80);


    @Override
    public void onCreate() {
        super.onCreate();
        initializeNetworkReceiver();
    }

    private static boolean networking;
    public static boolean isNetWorkingAvailable() {
        return networking;
    }
    private void initializeNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        NetworkReceiver networkReceiver = new NetworkReceiver(networking -> MyApplication.networking = networking);
        this.registerReceiver(networkReceiver, intentFilter);
        MyApplication.networking = NetworkReceiver.isConnected(this);
    }

}
