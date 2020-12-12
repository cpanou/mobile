package com.example.marvelcomics.ui.comics;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;

import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.ui.DiffItemCallback;

class ComicsAdapter extends PagedListAdapter<Comic, ComicViewHolder> {

    private final LayoutInflater inflater;

    protected ComicsAdapter(Context context) {
        super(new DiffItemCallback<>());
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comic_card, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {

    }

    @Override
    public void onCurrentListChanged(@Nullable PagedList<Comic> previousList, @Nullable PagedList<Comic> currentList) {
        super.onCurrentListChanged(previousList, currentList);
    }

}