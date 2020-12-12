package com.example.marvelcomics.ui.characters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.marvelcomics.R;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CharactersFragment extends Fragment {

    private CharacterViewModel characterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_characters, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.characters_list);
        CharacterAdapter adapter = new CharacterAdapter(getActivity());
        GridLayoutManager mLayoutManager = new GridLayoutManager(requireActivity().getApplicationContext(),
                3,  LinearLayoutManager.HORIZONTAL, false);
        final AtomicBoolean loading = new AtomicBoolean(false);
        final AtomicInteger scrollTo = new AtomicInteger(0);
        characterViewModel.getCharacters().observe(getViewLifecycleOwner(), (list) -> {
            scrollTo.set(getScrollTo(adapter));
            adapter.submitList(list);
            loading.set(false);
            root.findViewById(R.id.loader).setVisibility(View.GONE);
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mLayoutManager.scrollToPosition(scrollTo.get());
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int shownItems = (mLayoutManager.getChildCount() + mLayoutManager.findFirstVisibleItemPosition());
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1) &&
                        shownItems == mLayoutManager.getItemCount() && !loading.get()) {
                    root.findViewById(R.id.loader).setVisibility(View.VISIBLE);
                    loading.set(characterViewModel.queryUpdate());
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private int getScrollTo(CharacterAdapter adapter) {
        int scrollTo = 0;
        if (adapter.getCurrentList() != null) {
            scrollTo = adapter.getItemCount() - 1;
        }
        return scrollTo;
    }


}