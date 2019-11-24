package com.example.waoquiz.ui.home;

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
import com.example.waoquiz.network.NetworkManager;


import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeFragment extends Fragment {

    private final String BACKEND = "http://104.248.39.207:9090/themes";
    private HomeViewModel homeViewModel;
    private ThemeAdapter adapter;

    private final NetworkManager.OnRequestCompleteListener listener =
        new NetworkManager.OnRequestCompleteListener() {
            @Override
            public void onRequestComplete(final String body) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refresh(body);
                    }
                });
            }
        };

    private TextView body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RecyclerView recycler = root.findViewById(R.id.list_view);
        adapter = new ThemeAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> themes) {
                if (themes != null) {
                    adapter.setThemes(themes);
                }
            }
        };

        homeViewModel
                .getThemes()
                .observe(getViewLifecycleOwner(), observer);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NetworkManager.getInstance().get(BACKEND, listener);
        NetworkManager.getInstance().addListener(listener);
    }

    private void refresh(final String body) {
        List<String> arr = new ArrayList<>(Arrays.asList(body.split(",")));
        adapter.setThemes(arr);
    }
}