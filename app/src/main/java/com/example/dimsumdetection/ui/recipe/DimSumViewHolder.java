package com.example.dimsumdetection.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.RecipeDetailActivity;
import com.example.dimsumdetection.database.DataHandler;
import com.example.dimsumdetection.database.SavePreference;
import com.squareup.picasso.Picasso;

public class DimSumViewHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    private ImageView imageView;
    private TextView textView_name;
    private ImageView image_favorite;

    public DimSumViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        imageView = itemView.findViewById(R.id.imgView);
        textView_name = itemView.findViewById(R.id.textView_name);
        image_favorite = itemView.findViewById(R.id.image_favorite);
    }

    public void setImageView(String url) {
        Picasso.get().load(url).into(imageView);
    }

    public void setName(String name) {
        textView_name.setText(name);
    }

    public void setCardView(Context mContext, int recipeid){
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, RecipeDetailActivity.class);
            intent.putExtra("RecipeID", recipeid);
            mContext.startActivity(intent);
        });
    }

    public void setFavorite(Context mContext, DimSum dimsum){
        image_favorite.setOnClickListener(v ->{
            DataHandler.getInstance().AddDimSumFavoriteList(mContext, dimsum);
            Toast.makeText(mContext, "The Favorite is added!", Toast.LENGTH_SHORT).show();
        });
    }
}