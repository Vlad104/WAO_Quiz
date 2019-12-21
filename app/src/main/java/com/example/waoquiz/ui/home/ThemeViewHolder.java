package com.example.waoquiz.ui.home;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.waoquiz.R;
import com.example.waoquiz.IEventListener;

public class ThemeViewHolder extends RecyclerView.ViewHolder {
    private Button themesContainer;
    private Context context;
    private IEventListener clickListener;

    ThemeViewHolder(@NonNull View itemView, IEventListener clickListener_) {
        super(itemView);
        themesContainer = itemView.findViewById(R.id.theme_item);
        context = itemView.getContext();
        clickListener = clickListener_;
    }

    public void setTheme(String theme) {
        themesContainer.setText(theme);
        themesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theme = themesContainer.getText().toString();
                clickListener.onGameStart(theme);
            }
        });
    }
}
