package com.m7mdabaza.emlenotes.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m7mdabaza.emlenotes.R;
import com.m7mdabaza.emlenotes.pojo.FavoriteModel;
import com.m7mdabaza.emlenotes.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteRecycleAdapter extends RecyclerView.Adapter<FavoriteRecycleAdapter.FavoriteViewHolder> {

    private ArrayList<FavoriteModel> FavoriteModel;
    private MainActivity mContext;

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycle_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteModel favoriteModel = FavoriteModel.get(position);

        holder.name.setText(favoriteModel.getName());
        String imageURL = favoriteModel.getPhoto();
        Picasso.get().load(imageURL).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return FavoriteModel.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView pic;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_item);
            pic = itemView.findViewById(R.id.pic_item);
        }

    }

    public void setList(ArrayList<FavoriteModel> FavoriteModel, MainActivity mContext) {
        this.FavoriteModel = FavoriteModel;
        this.mContext = mContext;

    }

}
