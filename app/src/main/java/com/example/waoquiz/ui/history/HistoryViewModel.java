package com.example.waoquiz.ui.history;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class HistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> mHistory;

    public HistoryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is history fragment");

        mHistory = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getHistory() {
        return mHistory;
    }
}