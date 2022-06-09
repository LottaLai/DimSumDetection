package com.example.dimsumdetection.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.dimsumdetection.object.DimSum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SavePreference {
    //Key
    private static String EMAIL = "email";
    private static String USERNAME = "username";
    private static String PASSWORD = "password";
    private static String DIMSUMFAVORITELIST = "DimSumFavoriteList";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    private static void editor(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (editor != null) {
                editor.putString(key, value).apply();
            }
        }
    }

    public static String GetEmail(Context context) {
        return getSharedPreferences(context).getString(EMAIL, "");
    }

    public static void SetEmail(Context context, String email) {
        editor(context, EMAIL, email);
    }

    public static String GetUsername(Context context) {
        return getSharedPreferences(context).getString(USERNAME, "");
    }

    public static void SetUsername(Context context, String username) {
        editor(context, USERNAME, username);
    }

    public static ArrayList<DimSum> GetDimSumFavoriteList(Context context){
        ArrayList<DimSum> dimSumList;
        Type type = new TypeToken<ArrayList<DimSum>>() {}.getType();
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(DIMSUMFAVORITELIST, null);
        dimSumList = gson.fromJson(json, type);
        return dimSumList == null ? new ArrayList<DimSum>() : dimSumList;
    }

    public static void SetDimSumFavoriteList(Context context, List<DimSum> dimSumList){
        Gson gson = new Gson();
        String json = gson.toJson(dimSumList);
        editor(context, DIMSUMFAVORITELIST, json);
        System.out.println(json);
    }

}
