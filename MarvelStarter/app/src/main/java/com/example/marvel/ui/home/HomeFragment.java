package com.example.marvel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.repository.entities.Character;

public class HomeFragment extends Fragment {

    private CharacterViewModel characterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.character_list);
        CharacterAdapter characterAdapter = new CharacterAdapter(getActivity());
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        characterViewModel.getCharacterLiveData().observe(getViewLifecycleOwner(), characters -> {
            characterAdapter.setCharacterList(characters);
        });
        characterViewModel.updateCharacters();
        return root;
    }
}