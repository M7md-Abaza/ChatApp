package com.m7mdabaza.emlenotes.adapters;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.m7mdabaza.emlenotes.R;
import com.m7mdabaza.emlenotes.pojo.RecentModel;
import com.m7mdabaza.emlenotes.ui.ChatActivity;
import com.m7mdabaza.emlenotes.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecentRecycleAdapter extends RecyclerView.Adapter<RecentRecycleAdapter.RecentViewHolder> {

    private ArrayList<RecentModel> RecentModel;

    private MainActivity mContext;

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_recycle_item, parent, false);
        return new RecentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        RecentModel recentModel = RecentModel.get(position);

        holder.name.setText(recentModel.getName());
        holder.message.setText(recentModel.getMessage());
        holder.time.setText(recentModel.getTime());
        if (recentModel.get_new() != 0) {
            holder._new.setText(recentModel.get_new().toString());
            holder._new.setVisibility(View.VISIBLE);
        }
        String imageURL = recentModel.getPic();
        Picasso.get().load(imageURL).into(holder.pic);
        if (position % 2 == 1) {
            holder.main.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(view -> {
            if (position == 0) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("name", recentModel.getName());
                intent.putExtra("imageURL", recentModel.getPic());

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(holder.pic, "ImageTransition");
                pairs[1] = new Pair<View, String>(holder.name, "NameTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mContext, pairs);

                mContext.startActivity(intent, options.toBundle());
            }
        });

        if (position == 0) {
            holder.main.getLayoutParams().height = 300;
        }

    }

    @Override
    public int getItemCount() {
        return RecentModel.size();
    }

    static class RecentViewHolder extends RecyclerView.ViewHolder {

        TextView name, message, time, _new;
        ShapeableImageView pic;
        ConstraintLayout main;

        RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_item);
            message = itemView.findViewById(R.id.message_item);
            time = itemView.findViewById(R.id.time_item);
            _new = itemView.findViewById(R.id.new_item);
            pic = itemView.findViewById(R.id.pic_item);
            main = itemView.findViewById(R.id.main_item);
        }

    }

    public void setList(ArrayList<RecentModel> RecentModel, MainActivity mContext) {
        this.RecentModel = RecentModel;
        this.mContext = mContext;

    }

}
