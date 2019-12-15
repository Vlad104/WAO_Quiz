package com.example.waoquiz.game;

import java.util.List;
import java.util.Collections;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import android.util.Log;
import java.util.ArrayList;
import java.text.ParseException;

import com.example.waoquiz.network.GameApi;
import com.example.waoquiz.network.ApiRepo;

public class GameRepo {
    private final static MutableLiveData<List<Game>> mGames = new MutableLiveData<>();

    static {
        mGames.setValue(Collections.<Game>emptyList());
    }

    private final Context mContext;
    private GameApi mGameApi;

    public GameRepo(Context context) {
        mContext = context;
        mGameApi = ApiRepo.from(mContext).getGameApi();
    }

    public LiveData<List<Game>> getGames() {
        return mGames;
    }

    public void refresh() {
        mGameApi.getAll().enqueue(new Callback<List<GameApi.GamePlain>>() {
            @Override
            public void onResponse(Call<List<GameApi.GamePlain>> call, Response<List<GameApi.GamePlain>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mGames.postValue(transform(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<GameApi.GamePlain>> call, Throwable t) {
                Log.e("GameRepo", "Failed to load", t);
            }
        });
    }

    private static List<Game> transform(List<GameApi.GamePlain> plains) {
        List<Game> result = new ArrayList<>();
        for (GameApi.GamePlain gamePlain : plains) {
            try {
                Game game = map(gamePlain);
                result.add(game);
                Log.e("LessonRepo", "Loaded " + game.theme);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Game map(GameApi.GamePlain gamePlain) throws ParseException {
        return new Game(
                gamePlain.theme,
                gamePlain.questions
        );
    }

    public Game getGame(final String theme) {
        List<Game> games = getGames().getValue();
        for (Game game : games) {
            if (game.theme == theme) {
                return game;
            }
        }

        return null;
    }
}
