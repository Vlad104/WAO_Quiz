package com.example.waoquiz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {History.class}, version = 1)
public abstract class HistoryDB extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
}
