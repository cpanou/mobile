package com.example.android.droidcafeinput.background;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.droidcafeinput.R;

public class BackgroundFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_background, container, false);
        Log.d(BackgroundFragment.class.getSimpleName(), "CREATED");

        setUpBackgroundTask(fragmentView);
        setUpBackgroundLoader(fragmentView);

        return fragmentView;
    }

    private void setUpBackgroundLoader(View fragmentView) {
        final TextView taskTextView = fragmentView.findViewById(R.id.bg_loader_text);
        final ProgressBar progressBar = fragmentView.findViewById(R.id.bg_loader_progress);
        Button taskButton = fragmentView.findViewById(R.id.bg_loader_button);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                taskTextView.setText("Loading...");
                getLoaderManager().restartLoader(MyBackgroundLoader.ID, null,
                        new LoaderManager.LoaderCallbacks<String>() {
                    @Override
                    public Loader<String> onCreateLoader(int id, Bundle args) {
                        return new MyBackgroundLoader(getActivity());
                    }
                    @Override
                    public void onLoadFinished(Loader<String> loader, String data) {
                        taskTextView.setText(data);
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onLoaderReset(Loader<String> loader) {
                    }
                });
            }
        });
    }

    private void setUpBackgroundTask(View fragmentView) {
        final TextView taskTextView = fragmentView.findViewById(R.id.bg_task_text);
        final ProgressBar progressBar = fragmentView.findViewById(R.id.bg_task_progress);
        Button taskButton = fragmentView.findViewById(R.id.bg_task_button);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyBackgroundTask(taskTextView, progressBar).execute();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(BackgroundFragment.class.getSimpleName(), "DESTROYED");
    }

}