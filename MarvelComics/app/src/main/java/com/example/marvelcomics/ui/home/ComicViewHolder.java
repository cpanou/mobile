package com.example.marvelcomics.ui.home;


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
    View card;
    ImageView imageView;
    TextView title;
    TextView description;

    public ComicViewHolder(@NonNull View cardView) {
        super(cardView);
        this.card = cardView;
        imageView = card.findViewById(R.id.comic_thumbnail);
        title = card.findViewById(R.id.comic_name);
        description = card.findViewById(R.id.comic_description);
    }

    public void bindTo(Comic comic) {
        ImageGateway.with(card, comic.getThumbnail())
                .draw(ImageVersion.PORTRAIT, ImageSize.fantastic)
                .into(imageView);
        title.setText(comic.getTitle());
        description.setText(comic.getDescription());
    }

    public void clear() {

    }

}