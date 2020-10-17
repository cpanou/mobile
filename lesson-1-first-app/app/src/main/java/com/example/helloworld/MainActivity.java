package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.example.helloworld.counter.CounterActivity;
import com.example.helloworld.counter.Messages;
import com.example.helloworld.scrollview.ArticleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchCounterActivity(View view) {
        Intent intent = new Intent(this, CounterActivity.class);
        intent.putExtra(Messages.COUNTER_VALUE, 20);
        startActivity(intent);
    }

    public void launchScrollActivity(View view) {
        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
    }

}