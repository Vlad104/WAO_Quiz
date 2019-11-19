package com.example.waoquiz.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameHistory.class , SimpleEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GameHistoryDao getGameHistoryDao();

    public abstract SimpleEntityDao getDao();

    private static AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }


    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "WAO").build();
    }
}