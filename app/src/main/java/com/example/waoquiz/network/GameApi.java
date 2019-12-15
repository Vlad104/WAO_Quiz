package com.example.waoquiz.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.waoquiz.game.Game;

public interface GameApi {
    
    class GamePlain {
        public String theme;
        public List<Game.Question> questions;
    }

    @GET("/")
    Call<List<GamePlain>> getAll();
}
