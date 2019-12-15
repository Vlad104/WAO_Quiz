package com.example.waoquiz.ui.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.waoquiz.game.Game;

public class GameViewModel extends ViewModel {

    private Game mGame;
    private int number = 0;

    public void setGame(Game game) {
        mGame = game;
    }

    public Game.Question getCurrentQuestion() {
        if (number < mGame.questions.size()) {
            return  mGame.questions.get(number);
        } else {
            return  mGame.questions.get(mGame.questions.size() - 1);
        }
    }

    public Boolean checkAnswer(String answerText) {
        Game.Question question = getCurrentQuestion();
        for (Game.Answer answer : question.answers) {
            if (answer.text.equals(answerText)) {
                return answer.rigth;
            }
        }

        return false;
    }

    public void nextQuestion() {
        if (number < mGame.questions.size()) {
            number++;
        }
    }

}