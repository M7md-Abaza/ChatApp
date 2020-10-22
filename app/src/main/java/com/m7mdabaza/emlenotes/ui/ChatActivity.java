package com.m7mdabaza.emlenotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.m7mdabaza.emlenotes.R;
import com.m7mdabaza.emlenotes.adapters.MessageRecycleAdapter;
import com.m7mdabaza.emlenotes.adapters.RecentRecycleAdapter;
import com.m7mdabaza.emlenotes.pojo.MessageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity implements OnClickListener {

    TextView name;
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

    private void displayMessages() {
        if (checkConnectivity()) {
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
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgBack) {
            onBackPressed();
        }
        if (view.getId() == R.id.btn_sendMessage) {
            String textMessage = edSendMessage.getText().toString();
            if (!textMessage.equals("")) {
                messagesViewModel.sendMessage(textMessage);
                messageRecycle.scrollToPosition(messageSize + i);
                edSendMessage.setText("");
                i = ++i;
            }
        }
    }

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

    private void getIntentData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("imageURL")) {
            String uName = getIntent().getStringExtra("name");
            name.setText(uName);
            String uImageURL = getIntent().getStringExtra("imageURL");
            Picasso.get().load(uImageURL).into(pic);
        }
    }

    void findViewById() {
        name = findViewById(R.id.name_chat);
        pic = findViewById(R.id.pic_chat);
        back = findViewById(R.id.imgBack);
        messageRecycle = findViewById(R.id.messageRecycle);
        edSendMessage = findViewById(R.id.et_textMessage);
        sendMessage = findViewById(R.id.btn_sendMessage);

        back.setOnClickListener(this);
        sendMessage.setOnClickListener(this);

    }
}