package com.example.marvelcomics.ui.characters;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Character;
import com.example.marvelcomics.repository.gateway.ImageGateway;
import com.example.marvelcomics.repository.gateway.images.ImageSize;
import com.example.marvelcomics.repository.gateway.images.ImageVersion;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    View card;
    ImageView imageView;
    TextView title;
    TextView description;
    TextView comics;
    TextView stories;
    TextView series;

    public CharacterViewHolder(@NonNull View cardView) {
        super(cardView);
        this.card = cardView;
        imageView = card.findViewById(R.id.character_thumbnail);
        title = card.findViewById(R.id.character_name);
        description = card.findViewById(R.id.character_description);
        comics = card.findViewById(R.id.appears_in_comics_value);

    }

    public void bindTo(Character character) {
        ImageGateway.with(card, character.getThumbnailUrl())
                .draw(ImageVersion.STANDARD, ImageSize.xlarge)
                .into(imageView);
        title.setText(character.getName());
        if(description != null) description.setText(character.getDescription());
        if(comics != null) comics.setText(String.valueOf(character.getComics_available()));
        if(series != null) series.setText(String.valueOf(character.getSeries_available()));
        if(stories != null) stories.setText(String.valueOf(character.getStories_available()));
    }

    public void clear() {

    }

}