package com.example.waoquiz.network;

import android.content.Context;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import com.example.waoquiz.ApplicationModified;

public class ApiRepo {
    private final GameApi mGameApi;
    private final OkHttpClient mOkHttpClient;
    private final String HOST = "10.0.2.2";
    private final int PORT = 3333;

    public ApiRepo() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder()
                        .scheme("http")
                        .host(HOST)
                        .port(PORT)
                        .build())
                .client(mOkHttpClient)
                .build();

        mGameApi = retrofit.create(GameApi.class);
    }

    public GameApi getGameApi() {
        return mGameApi;
    }

    public static ApiRepo from(Context context) {
        return ApplicationModified.from(context).getApis();
    }
}
