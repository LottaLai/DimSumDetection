package com.example.dimsumdetection.ui.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;

import java.util.ArrayList;

public class DimSumRecyclerViewAdapter extends RecyclerView.Adapter<DimSumViewHolder>{
    private ArrayList<DimSum> dimsumList;
    private Context mContext;

    public DimSumRecyclerViewAdapter(Context mContext, ArrayList<DimSum> recipeList) {
        this.mContext = mContext;
        this.dimsumList = recipeList;
    }

    @NonNull
    @Override
    public DimSumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_dimsum_cell, parent, false);
        return new DimSumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DimSumViewHolder holder, int position) {
        holder.setImageView(dimsumList.get(position).getImageUrl());
        holder.setName(dimsumList.get(position).getName());
        holder.setCardView(mContext, dimsumList.get(position).getRecipeId());
        holder.setFavorite(mContext, dimsumList.get(position));
    }

    @Override
    public int getItemCount() {
        return dimsumList.size();
    }

}
