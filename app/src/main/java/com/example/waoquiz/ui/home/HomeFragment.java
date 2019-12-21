package com.example.waoquiz.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waoquiz.R;
import com.example.waoquiz.game.Game;
import java.util.List;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.waoquiz.IEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ThemeAdapter adapter;
    private IEventListener clickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (IEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.list_view);
        adapter = new ThemeAdapter(clickListener);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Observer<List<Game>> observer = new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                if (games == null) {
                    return;
                }

                List<String> themes = new ArrayList<>();
                for (Game game : games) {
                    themes.add(game.theme);
                }

                adapter.setThemes(themes);
            }
        };

        homeViewModel = new ViewModelProvider(getActivity())
                .get(HomeViewModel.class);
        homeViewModel
                .getGames()
                .observe(getViewLifecycleOwner(), observer);

        homeViewModel.refresh();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}