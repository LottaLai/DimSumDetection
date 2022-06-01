package com.example.dimsumdetection.ui.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;

import java.util.ArrayList;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeViewHolder>{
    private ArrayList<DimSum> dimsumList;
    private Context mContext;
    public RecipeRecyclerViewAdapter(Context mContext, ArrayList<DimSum> recipeList) {
        this.mContext = mContext;
        this.dimsumList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.setImageView(dimsumList.get(position).getImageUrl());
        holder.setName(dimsumList.get(position).getName());
        holder.setCardView(mContext, dimsumList.get(position).getRecipeId());
    }

    @Override
    public int getItemCount() {
        return dimsumList.size();
    }

}
