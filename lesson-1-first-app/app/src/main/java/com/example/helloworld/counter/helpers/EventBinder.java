package com.example.helloworld.counter.helpers;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.counter.CounterActivity;

import java.util.function.Consumer;

public class EventBinder {

    /**
     *  Create a variable to store the activity name, to Use it as a TAG to filter the logs.
     * */
    private static final String LOG_TAG = CounterActivity.class.getSimpleName();

    public static void setButtonHandler(Button button, Consumer<View> consumer) {
        Log.d(LOG_TAG, "init");
        //(1) - Method 1
        withMethodReference(button, consumer);
        //(2) - Method 2
        withLambda(button, consumer);
        //(3) - Method 3
        withAnonymousInnerClass(button, consumer);
    }

    //Method Reference
    private static void withMethodReference(Button button, Consumer<View> consumer)  {
        Log.d(LOG_TAG, "withMethodReference");
        button.setOnClickListener(consumer::accept);
    }

    //Lambda to provide a handler
    private static void withLambda(Button button, Consumer<View> consumer)  {
        Log.d(LOG_TAG, "withLambda");
        button.setOnClickListener(view -> consumer.accept(view));
    }

    //Anonymous Inner class ( implementation of OnClickListener() )
    private static void withAnonymousInnerClass(Button button, Consumer<View> consumer) {
        Log.d(LOG_TAG, "withAnonymous");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumer.accept(view);
            }
        });
    }

}
