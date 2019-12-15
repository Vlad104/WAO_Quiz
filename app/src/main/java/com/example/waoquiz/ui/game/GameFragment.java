package com.example.waoquiz.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waoquiz.IEventListener;
import com.example.waoquiz.R;
import com.example.waoquiz.game.Game;
import com.example.waoquiz.game.GameRepo;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {
    private GameViewModel gameViewModel;
    private IEventListener clickListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String theme = bundle.getString("THEME");
            gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
            gameViewModel.setGame(new GameRepo(getContext()).getGame(theme));
            update(gameViewModel.getCurrentQuestion());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (IEventListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    private void update(Game.Question question) {
        TextView questionText = getView().findViewById(R.id.game_question);
        questionText.setText(question.question);

        LinearLayout answersContainer = getView().findViewById(R.id.answers_container);
        answersContainer.removeAllViewsInLayout();
        for (Game.Answer answer : question.answers) {
            final Button newButton = new Button(getContext());
            newButton.setText(answer.text);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = newButton.getText().toString();
                    gameLogic(answer);
                }
            });
//            if (newButton.getParent() != null) {
//                ((ViewGroup) newButton.getParent()).removeView(newButton);
//            }
            answersContainer.addView(newButton);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }

    private void gameLogic(String answer) {
        if (gameViewModel.checkAnswer(answer)) {
            gameViewModel.nextQuestion();
            update(gameViewModel.getCurrentQuestion());
        } else {
            clickListener.onGameEnd();
        }
    }
}