package com.example.waoquiz.db;
import android.content.Context;

import androidx.room.Room;

public class DBHelper {
    private static  DBHelper sInstance;
    private Context mContext;
    private final HistoryDB mDb;

    public DBHelper(Context context) {
        mContext = context;
        mDb = Room.databaseBuilder(context, HistoryDB.class, "history.db")
                .allowMainThreadQueries()
                .build();
    }

    public HistoryDB getHistoriesDb() {
        return mDb;
    }

    public static DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }
}
