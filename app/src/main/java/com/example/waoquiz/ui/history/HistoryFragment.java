package com.example.waoquiz.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waoquiz.R;
import com.example.waoquiz.db.History;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    private HistoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyViewModel = new ViewModelProvider(getActivity())
                .get(HistoryViewModel.class);

        final TextView textView = view.findViewById(R.id.text_history);
        historyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RecyclerView recycler = view.findViewById(R.id.list_view);
        adapter = new HistoryAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        historyViewModel.getHistory().observe(getViewLifecycleOwner(), new Observer<List<History>>() {
                @Override
                public void onChanged(List<History> history) {
                    if (history != null) {
                        adapter.setHistory(history);
                    }
                }
            });
    }
}