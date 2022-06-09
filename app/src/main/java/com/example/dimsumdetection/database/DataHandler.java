package com.example.dimsumdetection.database;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.dimsumdetection.object.DimSum;

import java.util.ArrayList;

public class DataHandler {
    public static DataHandler INSTANCE = null;

    private ArrayList<DimSum> dimsum_favorite_list = new ArrayList<DimSum>();

    public DataHandler() {}
    public static DataHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataHandler();
        }
        return(INSTANCE);
    }


    public void AddDimSumFavoriteList(Context context, DimSum dimsum){
        dimsum_favorite_list = SavePreference.GetDimSumFavoriteList(context);
        if(dimsum_favorite_list.contains(dimsum)){
            return;
        }
        dimsum_favorite_list.add(dimsum);
        SavePreference.SetDimSumFavoriteList(context, dimsum_favorite_list);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void RemoveDimSumFavoriteList(Context context, DimSum dimsum){
        dimsum_favorite_list = SavePreference.GetDimSumFavoriteList(context);
        dimsum_favorite_list.removeIf(id -> (id.getID() == dimsum.getID()));
        SavePreference.SetDimSumFavoriteList(context, dimsum_favorite_list);
    }

    public ArrayList<DimSum> GetDimSumFavoriteList(){
        return dimsum_favorite_list;
    }
}
