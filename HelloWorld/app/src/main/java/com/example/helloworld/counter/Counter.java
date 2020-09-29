package com.example.helloworld.counter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Counter {
    private Context context;
    private Integer mCount;
    private TextView counterText;

    private Consumer<Integer> updateCounterText = (count) -> counterText.setText(String.valueOf(count));

    public Counter(Context context, TextView counterText) {
        this.counterText = counterText;
        this.context = context;
    }

    public Consumer<View> getCountUp() {
        return (view) -> updateCounterText.accept(++mCount);
    }

    public Consumer<View> getZeroCounter() {
        return (view) -> updateCounterText.accept(mCount = 0);
    }

    public Consumer<View> getShowToast() {
        return (view) -> Toast.makeText(context, "Count: " + mCount, Toast.LENGTH_LONG).show();
    }
}
