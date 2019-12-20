package com.example.waoquiz.ui.history;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.waoquiz.R;
import com.example.waoquiz.db.History;

class ViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private LinearLayout historyContainer;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        historyContainer = itemView.findViewById(R.id.history_container);
    }

    public void setHistory(History history) {
        TextView textViewTheme = new TextView(context);
        TextView textViewScore = new TextView(context);
        TextView textViewMaxScore = new TextView(context);

        textViewTheme.setText(history.theme);
        textViewScore.setText(String.valueOf(history.score));
        textViewMaxScore.setText(String.valueOf(history.maxScore));

        historyContainer.addView(textViewTheme);
        historyContainer.addView(textViewScore);
        historyContainer.addView(textViewMaxScore);
    }
}