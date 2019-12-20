package com.example.waoquiz.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM History")
    List<History> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistory(History history);

}
