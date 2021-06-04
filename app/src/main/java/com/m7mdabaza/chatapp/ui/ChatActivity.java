package com.m7mdabaza.chatapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.m7mdabaza.chatapp.R;
import com.m7mdabaza.chatapp.adapters.MessageRecycleAdapter;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity implements OnClickListener {

    TextView name, noInternet;
    ImageView pic, back;
    EditText edSendMessage;
    Button sendMessage;
    RecyclerView messageRecycle;
    MessagesViewModel messagesViewModel;
    LinearLayoutManager layoutManager;

    int messageSize;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        findViewById();
        getIntentData();
        displayMessages();


    }

    // method to observe the data which in the MessagesViewModel and display it in Message recycle
    private void displayMessages() {
        if (checkConnectivity()) {
            noInternet.setVisibility(View.INVISIBLE);
            // to tell the activity to be a listener for ViewModel
            messagesViewModel = ViewModelProviders.of(this).get(MessagesViewModel.class);
            //getting Data from ViewModel
            messagesViewModel.getHttpRequest();

            messagesViewModel.messagesMutableLiveData.observe(this, messageModels -> {
                messageSize = messageModels.size() - 1;

                messageRecycle.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                MessageRecycleAdapter messageAdapter = new MessageRecycleAdapter();
                messageAdapter.setList(messageModels);

                messageAdapter.notifyItemInserted(messageModels.size() - 1);
                messageRecycle.scrollToPosition(messageModels.size() - 1);
                messageRecycle.setAdapter(messageAdapter);

                //Next three lines to make move up chat messages when keyboard open
                layoutManager = new LinearLayoutManager(ChatActivity.this);
                layoutManager.setStackFromEnd(true);
                messageRecycle.setLayoutManager(layoutManager);
            });
        } else { // if no internet connection
            noInternet.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgBack) {
            onBackPressed();
        }
        // Button to send message
        if (view.getId() == R.id.btn_sendMessage) {
            if (checkConnectivity()) {
                String textMessage = edSendMessage.getText().toString();
                if (!textMessage.equals("")) {
                    messagesViewModel.sendMessage(textMessage);
                    messageRecycle.scrollToPosition(messageSize + i);
                    edSendMessage.setText("");
                    i = ++i;
                }
            } else { // if no internet connection
                Toast.makeText(ChatActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // to check if there is internet connection or not by return true or false
    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = Objects.requireNonNull(connectivityManager).getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true;
                } else return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
            }
        }

        return false;

    }

    // receive animated transition data from Intent
    private void getIntentData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("imageURL")) {
            String uName = getIntent().getStringExtra("name");
            name.setText(uName);
            String uImageURL = getIntent().getStringExtra("imageURL");
            Picasso.get().load(uImageURL).into(pic);
        }
    }

    //to declare Views to be readable in java
    void findViewById() {
        name = findViewById(R.id.name_chat);
        pic = findViewById(R.id.pic_chat);
        back = findViewById(R.id.imgBack);
        messageRecycle = findViewById(R.id.messageRecycle);
        edSendMessage = findViewById(R.id.et_textMessage);
        sendMessage = findViewById(R.id.btn_sendMessage);
        noInternet = findViewById(R.id.noInternet_ChatTxt);

        back.setOnClickListener(this);
        sendMessage.setOnClickListener(this);

        edSendMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!edSendMessage.getText().toString().equals("")) {
                    sendMessage.setEnabled(true);
                } else if (edSendMessage.getText().toString().equals("")) {
                    sendMessage.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}