package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.counter.Counter;

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
        Button toastButton = findViewById(R.id.toast_button);
        Button countButton = findViewById(R.id.count_button);
        Button zeroButton = findViewById(R.id.zero_button);
        TextView counterText = findViewById(R.id.show_count);
        Counter.bind(this, countButton, zeroButton, toastButton, counterText);
    }

}