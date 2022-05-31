package com.example.dimsumdetection.ui.recipe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView textView;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void setImageView(String url) {
        Picasso.get().load(url).into(imageView);
    }

    public void setName(String name) {
        textView.setText(name);
    }
}