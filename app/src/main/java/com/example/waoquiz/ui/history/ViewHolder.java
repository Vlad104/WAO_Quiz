package com.example.waoquiz.ui.history;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.waoquiz.R;
import com.example.waoquiz.db.History;

class ViewHolder extends RecyclerView.ViewHolder {

    ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setHistory(History history) {
        TextView textViewTheme = itemView.findViewById(R.id.history_theme);
        TextView textViewScore = itemView.findViewById(R.id.history_score);

        textViewTheme.setText(history.theme);
        textViewScore.setText(String.format("%d/%d", history.score, history.maxScore));
    }
}