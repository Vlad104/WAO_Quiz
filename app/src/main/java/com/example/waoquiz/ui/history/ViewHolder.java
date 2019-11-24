package com.example.waoquiz.ui.history;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.waoquiz.R;

class ViewHolder extends RecyclerView.ViewHolder {
    private TextView _textView;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        _textView = itemView.findViewById(R.id.item_history);
    }

    TextView getTextView() {
        return _textView;
    }

    public void setText(String text) {
        _textView.setText(text);
    }
}