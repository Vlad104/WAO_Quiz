package com.example.waoquiz.ui.game;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.AndroidViewModel;

import com.example.waoquiz.db.History;
import com.example.waoquiz.game.Game;
import com.example.waoquiz.db.DBHelper;

public class GameViewModel extends AndroidViewModel {

    private Game mGame;
    private int number = 0;

    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    public void setGame(Game game) {
        mGame = game;
    }

    public Game.Question getCurrentQuestion() {
        if (number >= mGame.questions.size()) {
            resetProgress();
        }
        return  mGame.questions.get(number);
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

    public boolean nextQuestion() {
        number++;
        if (number < mGame.questions.size()) {
            return true;
        }

        return false;
    }

    public void resetProgress() {
        number = 0;
    }

    public void gameEnd() {
        DBHelper.getInstance(getApplication())
                .getHistoriesDb()
                .getHistoryDao()
                .insertHistory(new History(mGame.theme, number, mGame.questions.size()));
    }

}