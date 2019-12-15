package com.example.waoquiz.ui.home;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.waoquiz.R;
import com.example.waoquiz.IEventListener;

import java.util.List;

public class ThemeViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private LinearLayout themesContainer;
    private Context context;
    private IEventListener clickListener;

    ThemeViewHolder(@NonNull View itemView, IEventListener clickListener_) {
        super(itemView);
        textView = itemView.findViewById(R.id.item_home);
        themesContainer = itemView.findViewById(R.id.themes_container);
        context = itemView.getContext();
        clickListener = clickListener_;
    }

    public void removeAllThemes() {
        themesContainer.removeAllViewsInLayout();
    }

    TextView getTextView() {
        return textView;
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setThemes(String theme) {
        removeAllThemes();
        final Button newButton = new Button(context);
        newButton.setText(theme);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theme = newButton.getText().toString();
                clickListener.onGameStart(theme);
            }
        });
        themesContainer.addView(newButton);
    }
}

