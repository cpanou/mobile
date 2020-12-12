package com.example.marvelcomics.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.ListAdapter;

import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.ui.DiffItemCallback;

import java.util.List;

class ComicsAdapter extends ListAdapter<Comic, ComicViewHolder> {

    private final LayoutInflater inflater;

    protected ComicsAdapter(Context context) {
        super(new DiffItemCallback<>());
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comic_sale_card, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = getItem(position) ;
        if(comic != null) {
            holder.bindTo(comic);
        }
    }

    @Override
    public void onCurrentListChanged(@Nullable List<Comic> previousList, @Nullable List<Comic> currentList) {
        super.onCurrentListChanged(previousList, currentList);
    }

}