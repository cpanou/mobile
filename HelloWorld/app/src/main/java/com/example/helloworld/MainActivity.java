package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.counter.Counter;
import com.example.helloworld.counter.EventBinder;

public class MainActivity extends AppCompatActivity {

    /**
     * Create a variable to store the activity name, to Use it as a TAG to filter the logs.
     */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * Equivalent to  public static void main() { ... } in traditional Java applications.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "MainActivity Started!");
        initialiseView();
    }

    private void initialiseView() {
        bindCounterView();
    }

    private void bindCounterView() {
        //(1) The Counter object needs the TextView
        TextView counterText = findViewById(R.id.show_count);
        //(2) Create the Counter Object
        Counter counter = new Counter(this, counterText);

        //(3) Get references to the buttons
        Button toastButton = findViewById(R.id.toast_button);
        Button countButton = findViewById(R.id.count_button);
        Button zeroButton = findViewById(R.id.zero_button);

        //(4) The eventBinder sets a click listener to a button following various methods.
        //We pass in a button and a Consumer so that they are bound together
        EventBinder.setButtonHandler(toastButton,  counter.getShowToast());
        EventBinder.setButtonHandler(countButton,  counter.getCountUp());
        EventBinder.setButtonHandler(zeroButton,  counter.getZeroCounter());
    }

}