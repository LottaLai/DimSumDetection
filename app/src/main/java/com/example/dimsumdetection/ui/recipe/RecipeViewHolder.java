package com.example.dimsumdetection.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.RecipeDetail;
import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    private ImageView imageView;
    private TextView textView;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        imageView = itemView.findViewById(R.id.imgView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void setImageView(String url) {
        Picasso.get().load(url).into(imageView);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setCardView(Context mContext, int recipeid){
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, RecipeDetail.class);
            intent.putExtra("RecipeID", recipeid);
            mContext.startActivity(intent);
        });
    }
}