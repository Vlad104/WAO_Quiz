package com.example.waoquiz.ui.home;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.waoquiz.R;

import java.util.List;

public class ThemeViewHolder extends RecyclerView.ViewHolder {
    private TextView _textView;
    private LinearLayout _themesContainer;
    private Context context;

    ThemeViewHolder(@NonNull View itemView) {
        super(itemView);
        _textView = itemView.findViewById(R.id.item_home);
        _themesContainer = itemView.findViewById(R.id.themes_container);
        context = itemView.getContext();
    }

    public void removeAllThemes() {
        _themesContainer.removeAllViewsInLayout();
    }

    TextView getTextView() {
        return _textView;
    }

    public void setText(String text) {
        _textView.setText(text);
    }

    public void setThemes(List<String> themes) {
        removeAllThemes();
        for (String theme : themes) {
            Button newButton = new Button(context);
            newButton.setText(theme);
            _themesContainer.addView(newButton);
        }
    }
}

