package com.m7mdabaza.emlenotes.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.m7mdabaza.emlenotes.R;
import com.m7mdabaza.emlenotes.adapters.FavoriteRecycleAdapter;
import com.m7mdabaza.emlenotes.adapters.RecentRecycleAdapter;


import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    RecyclerView favoriteRecycle, recentRecycle;
    ProgressBar progressFavorite, progressRecent;
    TextView noInternet, favorites;
    SwipeRefreshLayout pullToRefresh;

    FavoriteRecentViewModel favoriteRecentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        displayFavoriteRecentData();
        swipeToRefresh();

    }

    private void swipeToRefresh() {
        pullToRefresh.setOnRefreshListener(() -> {
            pullToRefresh.setRefreshing(false);
        });
    }

    private void displayFavoriteRecentData() {
        if (checkConnectivity()) {
            // to tell the activity to be a listener for ViewModel
            favoriteRecentViewModel = ViewModelProviders.of(this).get(FavoriteRecentViewModel.class);

            //getting Data from ViewModel
            favoriteRecentViewModel.getHttpRequest();

            favoriteRecentViewModel.favoriteMutableLiveData.observe(this, favoriteModels -> {
                progressFavorite.setVisibility(View.GONE);
                favoriteRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                FavoriteRecycleAdapter favoriteAdapter = new FavoriteRecycleAdapter();
                favoriteAdapter.setList(favoriteModels, MainActivity.this);
                favoriteRecycle.setAdapter(favoriteAdapter);
            });

            favoriteRecentViewModel.recentMutableLiveData.observe(this, recentModels -> {
                progressRecent.setVisibility(View.GONE);
                recentRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                RecentRecycleAdapter recentAdapter = new RecentRecycleAdapter();
                recentAdapter.setList(recentModels, MainActivity.this);
                recentRecycle.setAdapter(recentAdapter);

            });
        } else {
            progressFavorite.setVisibility(View.GONE);
            progressRecent.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            favorites.setVisibility(View.INVISIBLE);
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


    void findViewById() {
        favoriteRecycle = findViewById(R.id.favoriteRecycle);
        recentRecycle = findViewById(R.id.RecentRecycle);
        progressFavorite = findViewById(R.id.progress_favorite);
        progressRecent = findViewById(R.id.progress_recent);
        noInternet = findViewById(R.id.NoInternet_txt);
        favorites = findViewById(R.id.favorite_txt);

        pullToRefresh = findViewById(R.id.pullToRefresh);
    }


}
