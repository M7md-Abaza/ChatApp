package com.m7mdabaza.emlenotes.ui;


import com.m7mdabaza.emlenotes.pojo.FirstScreenResponse;
import com.m7mdabaza.emlenotes.pojo.SecondScreenResponse;

import retrofit2.Call;
import retrofit2.http.GET;

// Retrofit Interface
public interface RetrofitObjectInterFace {

    // first screen data Interface
    @GET("Screen_1.json")
    Call<FirstScreenResponse> getFirstScreenObjects();

    // second screen data Interface
    @GET("Screen_2.json")
    Call<SecondScreenResponse> getSecondScreenObjects();

}
