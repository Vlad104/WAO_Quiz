package com.example.waoquiz.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GameHistoryDao {

    // Добавление Game в бд
    @Insert
    void insertAll(GameHistory... game);

    // Удаление Game из бд
    @Delete
    void delete(GameHistory game);

    // Получение всех Game из бд
    @Query("SELECT * FROM game")
    List<GameHistory> getAllGame();
}