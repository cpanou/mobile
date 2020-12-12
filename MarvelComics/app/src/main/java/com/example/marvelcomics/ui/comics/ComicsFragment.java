package com.example.marvelcomics.ui.comics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.marvelcomics.MyApplication;
import com.example.marvelcomics.R;

public class ComicsFragment extends Fragment {

    private ComicsViewModel comicsViewModel;
    private String text;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comics, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        comicsViewModel = new ViewModelProvider(this).get(ComicsViewModel.class);
//        comicsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


}