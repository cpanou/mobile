package com.example.marvelcomics;

import android.os.Bundle;

import com.example.marvelcomics.ui.characters.CharactersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import java.util.Objects;

public class ResourceGridActivity extends AppCompatActivity {
    public static final String GRID_KEY = "KEY";

    public static final String CHARACTERS_META = "CHARACTERS";
    public static final String COMICS_META = "COMICS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null && getIntent().getStringExtra(GRID_KEY).equalsIgnoreCase(CHARACTERS_META)) {
            toolbar.setTitle("Characters");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CharactersFragment())
                    .commitNow();
        }
    }

}