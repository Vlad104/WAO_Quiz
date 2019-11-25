package com.example.waoquiz.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> mThemes;

    public HomeViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Выберите тему для игры");

        mThemes = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getThemes() {
        return mThemes;
    }
}
