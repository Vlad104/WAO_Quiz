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

    private MutableLiveData<String> mText;
    private MutableLiveData<List<History>> histories = new MutableLiveData<>();

    public HistoryViewModel(@NonNull Application application) {
        super(application);
//        DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().insertHistory(new History("C++", 7, 10));
//        DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().insertHistory(new History("Rust", 1, 2));

        mText = new MutableLiveData<>();
        mText.setValue("История игр");

        histories.setValue(DBHelper.getInstance(getApplication()).getHistoriesDb().getHistoryDao().getAll());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<History>> getHistory() {
        return histories;
    }
}