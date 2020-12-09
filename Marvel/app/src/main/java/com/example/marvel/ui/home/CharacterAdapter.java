package com.example.marvel.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.repository.entities.Character;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final LayoutInflater inflater;
    private List<Character> characterList;

    CharacterAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.character_view, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        if(character!=null)
            holder.bindTo(character);
    }

    @Override
    public int getItemCount() {
        if (characterList != null)
            return characterList.size();
        else return 0;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
        notifyDataSetChanged();
    }


    public static class CharacterViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CharacterViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.characer_name);
        }

        public void bindTo(Character character) {
            textView.setText(character.getName());
        }
    }
}
