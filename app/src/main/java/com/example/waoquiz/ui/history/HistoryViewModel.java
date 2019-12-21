package com.example.waoquiz.ui.history;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import com.example.waoquiz.db.DBHelper;
import com.example.waoquiz.db.History;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel  {
    private MutableLiveData<List<History>> histories = new MutableLiveData<>();

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        histories.setValue(DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().getAll());
    }

    public LiveData<List<History>> getHistory() {
        return histories;
    }

    public void update() {
        histories.setValue(DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().getAll());
    }

    public void reset() {
        DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().deleteAll();
        update();
    }
}
