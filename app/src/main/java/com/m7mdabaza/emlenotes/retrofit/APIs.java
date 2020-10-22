package com.m7mdabaza.emlenotes.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIs {

    // https://emlenotes.com/challenges/android/Screen_1.json
    // https://emlenotes.com/challenges/android/Screen_2.json

    private static Retrofit retrofit;

    public static Retrofit getRetrofit1() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://emlenotes.com/challenges/android/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit2() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://emlenotes.com/challenges/android/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

