package com.example.android.droidcafeinput.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.droidcafeinput.R;


public class TabFragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TabFragment1.class.getSimpleName(), "CREATED");
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TabFragment1.class.getSimpleName(), "DESTROYED");
    }
}