package com.example.waoquiz.ui.home;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.waoquiz.R;

public class ThemeViewHolder extends RecyclerView.ViewHolder {
    private TextView _textView;

    ThemeViewHolder(@NonNull View itemView) {
        super(itemView);
        _textView = itemView.findViewById(R.id.item_home);
    }

    TextView getTextView() {
        return _textView;
    }

    public void setText(String text) {
        _textView.setText(text);
    }
}

