package com.example.dimsumdetection.ui.favorite;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.ui.recipe.DimSum;

import java.util.ArrayList;

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteViewHolder>{
    private ArrayList<DimSum> dimsumList;
    private Context mContext;
    private Fragment mFragment;

    public FavoriteRecyclerViewAdapter(Context mContext, Fragment mFragment, ArrayList<DimSum> recipeList) {
        this.mFragment = mFragment;
        this.mContext = mContext;
        this.dimsumList = recipeList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remove_dimsum_cell, parent, false);
        return new FavoriteViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.setImageView(dimsumList.get(position).getImageUrl());
        holder.setName(dimsumList.get(position).getName());
        holder.setCardView(mContext, dimsumList.get(position).getRecipeId());
        holder.setFavorite(mContext, mFragment, dimsumList.get(position));
    }

    @Override
    public int getItemCount() {
        return dimsumList.size();
    }

}
