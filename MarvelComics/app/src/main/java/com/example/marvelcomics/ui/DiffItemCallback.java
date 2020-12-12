package com.example.marvelcomics.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class DiffItemCallback<T> extends DiffUtil.ItemCallback<T> {

    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return false;
    }
}
