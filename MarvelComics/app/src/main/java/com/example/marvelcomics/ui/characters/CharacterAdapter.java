package com.example.marvelcomics.ui.characters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.marvelcomics.R;
import com.example.marvelcomics.repository.entities.Character;

class CharacterAdapter extends PagedListAdapter<Character, CharacterViewHolder> {

    private final LayoutInflater inflater;

    protected CharacterAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.inflater = LayoutInflater.from(context);
    }

    private static final DiffUtil.ItemCallback<Character> DIFF_CALLBACK = new DiffUtil.ItemCallback<Character>() {
        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem == newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return !oldItem.getId().equals(newItem.getId());
        }
    };

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.character_card, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = getItem(position);
        if (character != null) {
            holder.bindTo(character);
        } else holder.clear();
    }

    @Override
    public void onCurrentListChanged(@Nullable PagedList<Character> previousList, @Nullable PagedList<Character> currentList) {
        super.onCurrentListChanged(previousList, currentList);
    }

}