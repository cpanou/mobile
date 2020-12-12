package com.example.marvelcomics.ui.comics;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Comic;
import com.example.marvelcomics.repository.gateway.ImageGateway;
import com.example.marvelcomics.repository.gateway.images.ImageSize;
import com.example.marvelcomics.repository.gateway.images.ImageVersion;

public class ComicViewHolder extends RecyclerView.ViewHolder {

    public ComicViewHolder(@NonNull View cardView) {
        super(cardView);
    }

    public void bindTo(Comic comic) {

    }

    public void clear() {

    }

}