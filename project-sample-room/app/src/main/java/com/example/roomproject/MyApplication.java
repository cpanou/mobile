package com.example.roomproject;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
}
