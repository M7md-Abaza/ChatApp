package com.m7mdabaza.chatapp.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.m7mdabaza.chatapp.pojo.MessageModel;
import com.m7mdabaza.chatapp.pojo.SecondScreenResponse;
import com.m7mdabaza.chatapp.retrofit.APIs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesViewModel extends ViewModel {
    private static final String TAG = "MessagesViewModel";

    private final ArrayList<MessageModel> messagesList = new ArrayList<>();

    MutableLiveData<ArrayList<MessageModel>> messagesMutableLiveData = new MutableLiveData<>();


    void getHttpRequest() {
        RetrofitObjectInterFace retrofitObjectInterFace = APIs.getRetrofitSecondScreen().create(RetrofitObjectInterFace.class);
        Call<SecondScreenResponse> call = retrofitObjectInterFace.getSecondScreenObjects();
        call.enqueue(new Callback<SecondScreenResponse>() {
            @Override
            public void onResponse(Call<SecondScreenResponse> call, Response<SecondScreenResponse> response) {
                Log.d(TAG, "MAA 2 onResponse: done ");
                assert response.body() != null;
                for (int i = 0; i < response.body().getMessages().size(); i++) {
                    messagesList.add(new MessageModel(
                            response.body().getMessages().get(i).getMessage(),
                            response.body().getPic(),
                            response.body().getMessages().get(i).getSender()));
                }
                messagesMutableLiveData.setValue(messagesList);

            }

            @Override
            public void onFailure(Call<SecondScreenResponse> call, Throwable t) {
                Log.d(TAG, "MAA 2 onFailure: fail");
            }
        });
    }

    // method sendMessage for get sent message and add it to messagesList
    void sendMessage(String message) {
        messagesList.add(new MessageModel(message, "", 1));
    }

}


