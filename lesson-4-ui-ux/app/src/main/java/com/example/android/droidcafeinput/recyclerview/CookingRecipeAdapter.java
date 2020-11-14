package com.example.android.droidcafeinput.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.droidcafeinput.R;

import java.util.List;

public class CookingRecipeAdapter extends RecyclerView.Adapter<CookingRecipeAdapter.RecipeViewHolder> {
    private final List<String> recipeList;
    private final LayoutInflater inflater;
    private final Activity context;

    public CookingRecipeAdapter(List<String> recipeList, Activity context) {
        this.recipeList = recipeList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = inflater.inflate(R.layout.recycler_item, parent, false);
        return new RecipeViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        holder.textView.setText(recipeList.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCompat.IntentBuilder.from(context)
                        .setText("This is the recipe with id: "+ holder.getAdapterPosition())
                        .setChooserTitle("Where to Share")
                        .setType("text/plain")
                        .startChooser();
//                Toast.makeText(context, "Sharing the recipe with id: "+ holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button button;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            button = itemView.findViewById(R.id.item_button);
        }
    }

}
