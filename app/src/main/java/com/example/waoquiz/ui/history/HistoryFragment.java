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
import androidx.lifecycle.ViewModelProviders;

import com.example.waoquiz.R;
import com.example.waoquiz.db.DbManager;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    private HistoryAdapter adapter;

    private final DbManager.ReadAllListener<String> readListener = new DbManager.ReadAllListener<String>() {
        @Override
        public void onReadAll(final Collection<String> allItems) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    update(allItems);
                }
            });
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TextView textView = root.findViewById(R.id.text_history);

        final DbManager manager = DbManager.getInstance(getActivity());
        manager.insert("Each time I add this");
        manager.readAll(readListener);

        historyViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RecyclerView recycler = root.findViewById(R.id.list_view);
        adapter = new HistoryAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> history) {
                if (history != null) {
                    adapter.setHistory(history);
                }
            }
        };

        historyViewModel
                .getHistory()
                .observe(getViewLifecycleOwner(), observer);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void update(final Collection<String> list) {
        List<String> arr = new ArrayList<>(Arrays.asList(list.toArray(new String[0])));
        adapter.setHistory(arr);
    }
}