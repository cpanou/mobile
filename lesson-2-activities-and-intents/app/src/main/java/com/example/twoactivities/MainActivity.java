package com.example.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String MESSAGE_KEY = "message";

    public static final Integer REPLY_REQUEST_CODE = 1;
    public static final Integer REPLY_REQUEST_CODE_2 = 16;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");
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

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.message);
        String message = editText.getText().toString();

        Intent intent = new Intent(this, ReplyActivity.class);
        intent.putExtra(MESSAGE_KEY, message);

        startActivityForResult(intent, REPLY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "onActivityResult");
        //Which activity returned
        if (requestCode == REPLY_REQUEST_CODE) {
            //Check if the action completed successfully
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(MESSAGE_KEY);
                TextView textView = findViewById(R.id.reply);
                textView.setText(reply);
            } else {
                Toast.makeText(this, "User exit", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == REPLY_REQUEST_CODE_2) {
            //SECOND ACTIVITY FINISHED
            //GET RESULT
        }
    }


}