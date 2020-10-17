package com.example.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.twoactivities.MainActivity.MESSAGE_KEY;

public class ReplyActivity extends AppCompatActivity {
    private final String LOG_TAG = ReplyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_reply);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);

        TextView textView = findViewById(R.id.received_message);
        textView.setText(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    public void reply(View view) {
        Log.d(LOG_TAG, "Reply Called");

        //Gets a reference to the Input Object
        EditText editText = findViewById(R.id.message_reply);
        //Get the text from the input and store it in a local variable
        String replyMsg = editText.getText().toString();

        //Create a new Intent for the Main Activity.
        Intent intent = new Intent();
        //Store the message in the intent object
        intent.putExtra(MESSAGE_KEY, replyMsg);

        //Set the Result of the reply activity to success and pass the intent object
        setResult(RESULT_OK, intent);
        finish();
    }


}