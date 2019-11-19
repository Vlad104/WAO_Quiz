package com.example.waoquiz.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.waoquiz.R;
import com.example.waoquiz.db.DbManager;

import java.util.Collection;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;

    private final DbManager.ReadAllListener<String> readListener = new DbManager.ReadAllListener<String>() {
        @Override
        public void onReadAll(final Collection<String> allItems) {
            new Thread() {
                @Override
                public void run() {
                    showStringList(allItems);
                }
            };
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TextView textView = root.findViewById(R.id.text_history);
        historyViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final DbManager manager = DbManager.getInstance(getActivity());
        manager.insert("Test");
        manager.readAll(readListener);
    }

    private void showStringList(final Collection<String> list) {
        new AlertDialog.Builder(getActivity())
                .setItems(list.toArray(new String[0]), null)
                .show();
    }
}