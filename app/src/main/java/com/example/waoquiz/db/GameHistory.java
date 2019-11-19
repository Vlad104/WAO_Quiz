package com.example.waoquiz.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "game")
public class GameHistory {
    @PrimaryKey
    @NonNull
    String title;
    int score;
}