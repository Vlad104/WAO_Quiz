package com.example.waoquiz;

import android.app.Application;
import android.content.Context;
import com.example.waoquiz.network.ApiRepo;

public class ApplicationModified extends Application {
    private ApiRepo mApiRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiRepo = new ApiRepo();
    }

    public ApiRepo getApis() {
        return mApiRepo;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
