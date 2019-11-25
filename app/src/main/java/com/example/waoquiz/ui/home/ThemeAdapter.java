package com.example.waoquiz.ui.home;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;

import java.util.List;
import java.util.ArrayList;

import com.example.waoquiz.R;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeViewHolder> {

    private List<String> mThemes = new ArrayList<>();

    public void setThemes(List<String> themes) {
        mThemes = themes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThemeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        final String themes = mThemes.get(position);
        holder.setThemes(mThemes);
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }
}
