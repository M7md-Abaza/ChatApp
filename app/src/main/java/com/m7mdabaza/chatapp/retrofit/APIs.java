package com.m7mdabaza.chatapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//APIs class to handle api by Retrofit

public class APIs {

    // BaseURL = https://emlenotes.com/challenges/android/Screen_1.json
    // BaseURL = https://emlenotes.com/challenges/android/Screen_2.json

    private static Retrofit retrofit;

    // for first screen data
    public static Retrofit getRetrofitFirstScreen() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://emlenotes.com/challenges/android/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // for second screen data
    public static Retrofit getRetrofitSecondScreen() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://emlenotes.com/challenges/android/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

