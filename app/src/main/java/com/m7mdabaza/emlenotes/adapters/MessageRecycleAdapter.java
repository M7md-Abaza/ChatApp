package com.m7mdabaza.emlenotes.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.m7mdabaza.emlenotes.R;
import com.m7mdabaza.emlenotes.pojo.MessageModel;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MessageRecycleAdapter extends RecyclerView.Adapter<MessageRecycleAdapter.MessageViewHolder> {

    private ArrayList<MessageModel> MessageModel;


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycle_item, parent, false);
        return new MessageViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageModel messageModel = MessageModel.get(position);
        Integer senderCode = messageModel.getSender();

        if (senderCode == 0) {
            holder.senderItem.setVisibility(View.GONE);
            holder.receiverItem.setVisibility(View.VISIBLE);

            holder.receiverMessage.setText(messageModel.getMessage());
            String imageURL = messageModel.getPic();
            Picasso.get().load(imageURL).into(holder.receiverPic);

        } else if (senderCode == 1) {
            holder.senderItem.setVisibility(View.VISIBLE);
            holder.receiverItem.setVisibility(View.GONE);

            holder.senderMessage.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return MessageModel.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView receiverMessage, senderMessage;
        ImageView receiverPic;
        ConstraintLayout receiverItem, senderItem;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMessage = itemView.findViewById(R.id.receiverMessage_item);
            receiverPic = itemView.findViewById(R.id.receiverPicChat_item);
            receiverItem = itemView.findViewById(R.id.receiverConstrain_item);
            senderMessage = itemView.findViewById(R.id.senderMessage_item);
            senderItem = itemView.findViewById(R.id.senderConstrain_item);
        }

    }

    public void setList(ArrayList<MessageModel> MessageModel) {
        this.MessageModel = MessageModel;
    }
}
