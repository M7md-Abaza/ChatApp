package com.m7mdabaza.emlenotes.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.m7mdabaza.emlenotes.pojo.FavoriteModel;
import com.m7mdabaza.emlenotes.pojo.FirstScreenResponse;
import com.m7mdabaza.emlenotes.pojo.RecentModel;
import com.m7mdabaza.emlenotes.retrofit.APIs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteRecentViewModel extends ViewModel {


    private final ArrayList<FavoriteModel> favoriteList = new ArrayList<>();
    private final ArrayList<RecentModel> recentList = new ArrayList<>();

    private static final String TAG = "FavoriteRecentViewModel";

    MutableLiveData<ArrayList<FavoriteModel>> favoriteMutableLiveData = new MutableLiveData<>();
    MutableLiveData<ArrayList<RecentModel>> recentMutableLiveData = new MutableLiveData<>();


    void getHttpRequest() {
        RetrofitObjectInterFace retrofitObjectInterFace = APIs.getRetrofit1().create(RetrofitObjectInterFace.class);
        Call<FirstScreenResponse> call = retrofitObjectInterFace.getObjects1();
        call.enqueue(new Callback<FirstScreenResponse>() {
            @Override
            public void onResponse(Call<FirstScreenResponse> call, Response<FirstScreenResponse> response) {
                Log.d(TAG, "MAA 1 onResponse: done ");
                // Response to handel Favorites List and display in RecyclerView
                assert response.body() != null;
                for (int i = 0; i < response.body().getFavorites().size(); i++) {
                    favoriteList.add(new FavoriteModel(response.body().getFavorites().get(i).getName(),
                            response.body().getFavorites().get(i).getPic()));
                }
                favoriteMutableLiveData.setValue(favoriteList);
                //favoriteRecentView.onGetFavoriteList();
                // Response to handel Recent List and display in RecyclerView
                for (int i = 0; i < response.body().getRecent().size(); i++) {
                    recentList.add(new RecentModel(response.body().getRecent().get(i).getName(),
                            response.body().getRecent().get(i).getMessage(),
                            response.body().getRecent().get(i).getTime(),
                            response.body().getRecent().get(i).getNew(),
                            response.body().getRecent().get(i).getPic()));
                }
                recentMutableLiveData.setValue(recentList);

            }

            @Override
            public void onFailure(Call<FirstScreenResponse> call, Throwable t) {
                Log.d(TAG, "MAA 1 onFailure: fail");
            }
        });

    }



}
