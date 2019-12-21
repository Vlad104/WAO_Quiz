package com.example.waoquiz.ui.game;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.waoquiz.IEventListener;
import com.example.waoquiz.R;
import com.example.waoquiz.game.Game;
import com.example.waoquiz.game.GameRepo;
import android.widget.LinearLayout;

public class GameFragment extends Fragment {
    private GameViewModel gameViewModel;
    private IEventListener clickListener;
    private Button rightButton;
    private Button nextButton;
    private boolean clickAllowed = true;

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
            final boolean isRight = answer.rigth;
            if (isRight) {
                rightButton = newButton;
            }

            newButton.setText(answer.text);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickAllowed) {
                        if (!isRight) {
                            newButton.setBackgroundColor(Color.RED);
                        }
                        gameLogic(isRight);
                    }
                }
            });
            answersContainer.addView(newButton);
        }
        nextButton = new Button(getContext());
        nextButton.setVisibility(View.INVISIBLE);
        answersContainer.addView(nextButton);
    }

    private void showRight() {
        if (rightButton == null) {
            return;
        }
        rightButton.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
        rightButton = null;
    }

    private void gameLogic(boolean isRight) {
        clickAllowed = false;
        showRight();
        if (isRight) {
            nextButton.setText("Верно! Следующий вопрос");
            nextButton.setVisibility(View.VISIBLE);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightAnswer();
                    clickAllowed = true;
                }
            });
        } else {
            nextButton.setText("Не верно. В меню");
            nextButton.setVisibility(View.VISIBLE);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wrongAnswer();
                    clickAllowed = true;
                }
            });
        }
    }

    private void rightAnswer() {
        boolean hasNext = gameViewModel.nextQuestion();
        if (hasNext) {
            update(gameViewModel.getCurrentQuestion());
        } else {
            gameViewModel.gameEnd();
            clickListener.onGameEnd();
        }
    }

    private void wrongAnswer() {
        gameViewModel.gameEnd();
        clickListener.onGameEnd();
    }
}