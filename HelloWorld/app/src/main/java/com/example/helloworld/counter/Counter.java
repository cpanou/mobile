package com.example.helloworld.counter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.util.function.Consumer;

public class Counter {
    private Context context;
    private Integer mCount = 0;
    private Button countButton;
    private Button zeroButton;
    private Button toastButton;
    private TextView counterText;

    private Consumer<Integer> updateCounterText = (count) -> counterText.setText(String.valueOf(count));
    private Consumer<View> countUp = (view) -> {
        view.setBackgroundColor(mCount % 2== 0 ? context.getColor(R.color.colorPrimary) : context.getColor(R.color.colorSecondary));
        if(mCount ==0) zeroButton.setBackgroundColor(context.getColor(R.color.colorWarn));
        updateCounterText.accept(++mCount);

    };
    private Consumer<View> zeroCounter = (view) -> updateCounterText.accept(mCount = 0);
    private Consumer<View> showToast = (view) -> Toast.makeText(context, "Count: " + mCount, Toast.LENGTH_LONG).show();

    public Counter(Context context, Button countButton, Button zeroButton, Button toastButton, TextView counterText) {
        this.countButton = countButton;
        this.zeroButton = zeroButton;
        this.toastButton = toastButton;
        this.counterText = counterText;
        this.context = context;
    }

    public static void bind(Context context, Button countButton, Button zeroButton, Button toastButton, TextView counterText) {
        new Counter(context, countButton, zeroButton, toastButton, counterText).setHandlers();
    }


    private Counter setHandlers() {
        this.toastButton.setOnClickListener(showToast::accept);
        this.countButton.setOnClickListener(countUp::accept);
        this.zeroButton.setOnClickListener(zeroCounter::accept);
        return this;
    }

    private Counter withEventHandler() {
        EventBinder.setButtonHandler(this.toastButton, this.showToast);
        EventBinder.setButtonHandler(this.countButton, this.countUp);
        EventBinder.setButtonHandler(this.zeroButton, this.zeroCounter);
        return this;
    }

}
