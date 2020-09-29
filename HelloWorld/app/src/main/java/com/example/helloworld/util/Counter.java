package com.example.helloworld.util;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.function.Consumer;

public class Counter {
    private Context context;
    private Integer mCount;
    private Button countButton;
    private Button zeroButton;
    private Button toastButton;
    private TextView counterText;

    private Consumer<Integer> updateCounterText = (count) -> counterText.setText(String.valueOf(count));
    private Consumer<View> countUp = (view) -> updateCounterText.accept(++mCount);
    private Consumer<View> zeroCounter = (view) -> updateCounterText.accept(mCount = 0);
    private Consumer<View> showToast = (view) -> Toast.makeText(context, "Count: " + mCount, Toast.LENGTH_LONG).show();

    public Counter(Context context, Button countButton, Button zeroButton, Button toastButton, TextView counterText) {
        this.countButton = countButton;
        this.zeroButton = zeroButton;
        this.toastButton = toastButton;
        this.counterText = counterText;
        this.context = context;
    }

    public static Counter create(Context context, Button countButton, Button zeroButton, Button toastButton, TextView counterText) {
        return new Counter(context, countButton, zeroButton, toastButton, counterText);
    }

    public void initializeHandlers() {
        EventHandler.setButtonHandler(toastButton, showToast);
        EventHandler.setButtonHandler(countButton, countUp);
        EventHandler.setButtonHandler(zeroButton, zeroCounter);
    }
}
