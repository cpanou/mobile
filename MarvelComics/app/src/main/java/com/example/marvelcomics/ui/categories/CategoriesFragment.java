package com.example.marvelcomics.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.marvelcomics.R;
import com.example.marvelcomics.ResourceGridActivity;

import static com.example.marvelcomics.ResourceGridActivity.CHARACTERS_META;
import static com.example.marvelcomics.ResourceGridActivity.GRID_KEY;

public class CategoriesFragment extends Fragment {


    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_browse, container, false);
        CardView cardView = root.findViewById(R.id.characters_category_card);
        cardView.setOnClickListener(this::startCharactersGrid);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void startCharactersGrid(View view) {
        Intent intent = new Intent(requireContext(), ResourceGridActivity.class);
        intent.putExtra(GRID_KEY, CHARACTERS_META);
        startActivity(intent);
    }

}