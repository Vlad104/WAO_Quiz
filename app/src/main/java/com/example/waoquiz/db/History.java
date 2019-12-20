package com.example.waoquiz.db;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History")
public class History {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String theme;

    public int score;

    public int maxScore;

    public History() {}

    public History(String theme_, int score_, int maxScore_) {
        theme = theme_;
        score = score_;
        maxScore = maxScore_;
    }
}
