package com.example.waoquiz.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import com.example.waoquiz.game.Game;
import com.example.waoquiz.game.GameRepo;
import androidx.lifecycle.AndroidViewModel;
import android.app.Application;
import androidx.annotation.NonNull;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private GameRepo mRepo = new GameRepo(getApplication());
    private LiveData<List<Game>> mGames = mRepo.getGames();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mText.setValue("Выберите тему для игры");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Game>> getGames() {
        return mGames;
    }

    public void refresh() {
        mRepo.refresh();
    }
}
