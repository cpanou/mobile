package com.example.android.droidcafeinput.background;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.droidcafeinput.R;

public class BackgroundFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_background, container,
                false);
        Log.d(BackgroundFragment.class.getSimpleName(), "CREATED");

        final TextView taskTextView = fragmentView.findViewById(R.id.bg_task_text);
        Button taskButton = fragmentView.findViewById(R.id.bg_task_button);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String start = "Starting";
//                taskTextView.setText(start);
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                String text =  "Awake at last after sleeping for " + 10000 + " milliseconds!";
//                taskTextView.setText(text);

                new MyBackgroundTask(taskTextView).execute();

            }
        });

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(BackgroundFragment.class.getSimpleName(), "DESTROYED");
    }

}