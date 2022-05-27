package com.example.dimsumdetection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavePreference {
    //Key
    private static String EMAIL = "email";
    private static String USERNAME = "username";
    private static String PASSWORD = "password";

    private static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    private static void editor(Context context, String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if(sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(editor != null){
                editor.putString(key, value).apply();
            }
        }
    }

    public static String GetEmail(Context context){
        return getSharedPreferences(context).getString(EMAIL, "");
    }

    public static void SetEmail(Context context, String email){
        editor(context, EMAIL, email);
    }

    public static String GetUsername(Context context){
        return getSharedPreferences(context).getString(USERNAME, "");
    }

    public static void SetUsername(Context context, String username){
        editor(context, USERNAME, username);
    }
}
