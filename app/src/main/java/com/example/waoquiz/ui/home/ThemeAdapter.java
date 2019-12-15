package com.example.waoquiz.ui.home;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;

import java.util.List;
import java.util.ArrayList;

import com.example.waoquiz.R;
import com.example.waoquiz.IEventListener;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeViewHolder> {

    private List<String> mThemes = new ArrayList<>();
    private IEventListener clickListener;

    public ThemeAdapter(IEventListener  clickListener_) {
        clickListener = clickListener_;
    }

    public void setThemes(List<String> themes) {
        mThemes = themes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThemeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        final String theme = mThemes.get(position);
        holder.setThemes(theme);
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        clickListener = null;
    }
}
