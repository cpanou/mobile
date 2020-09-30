package com.example.helloworld.counter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.counter.helpers.Counter;

import static com.example.helloworld.counter.Messages.COUNTER_VALUE;

public class CounterActivity extends AppCompatActivity {

    /**
     * Create a variable to store the activity name, to Use it as a TAG to filter the logs.
     */
    private static final String LOG_TAG = CounterActivity.class.getSimpleName();

    /**
     * Equivalent to  public static void main() { ... } in traditional Java applications.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Log.d(LOG_TAG, "MainActivity Started!");
        initialiseView();
    }

    private void initialiseView() {
        Intent intent = getIntent();
        int count = intent.getIntExtra(COUNTER_VALUE, 0);
        bindCounterView(count);
    }

    private void bindCounterView(int count) {
        Button toastButton = findViewById(R.id.toast_button);
        Button countButton = findViewById(R.id.count_button);
        Button zeroButton = findViewById(R.id.zero_button);
        TextView counterText = findViewById(R.id.show_count);
        Counter.init(this, count)
                .with(countButton, zeroButton, toastButton, counterText)
                .start();
    }

}