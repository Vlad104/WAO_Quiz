package com.example.waoquiz.ui.history;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;

import java.util.List;
import java.util.ArrayList;

import com.example.waoquiz.R;

class HistoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> mHystory = new ArrayList<>();

    public void setHistory(List<String> history) {
        mHystory = history;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String history = mHystory.get(position);
        holder.setText(history);
    }

    @Override
    public int getItemCount() {
        return mHystory.size();
    }
}