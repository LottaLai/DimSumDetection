package com.example.dimsumdetection.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.RecipeDetailActivity;
import com.example.dimsumdetection.database.DataHandler;
import com.example.dimsumdetection.ui.recipe.DimSum;
import com.squareup.picasso.Picasso;

public class FavoriteViewHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    private ImageView imageView;
    private TextView textView_name;
    private ImageView image_favorite;

    public FavoriteViewHolder(@NonNull View itemView) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFavorite(Context mContext, Fragment mFragment, DimSum dimsum){
        image_favorite.setOnClickListener(v ->{
            DataHandler.getInstance().RemoveDimSumFavoriteList(mContext, dimsum);
            Toast.makeText(mContext, "The Favorite is removed!", Toast.LENGTH_SHORT).show();
            ((FavoriteFragment)mFragment).RefreshFragment();
        });
    }
}