package com.example.android.droidcafeinput.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.droidcafeinput.R;

import java.util.LinkedList;

public class CookingFragment extends Fragment {
    private final LinkedList<String> recipes = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_cooking, container, false);
        // Inflate the layout for this fragment
        Log.d(CookingFragment.class.getSimpleName(), "CREATED");
        for(int i=0; i < 20 ; i++) {
            recipes.addLast("Recipe " + i);
        }
        CookingRecipeAdapter adapter = new CookingRecipeAdapter(recipes, getActivity());

        RecyclerView recyclerView = fragment.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(CookingFragment.class.getSimpleName(), "DESTROYED");
    }

}