package com.example.testvid.Other;

import android.content.Context;
import android.content.SharedPreferences;

//save token

public class Preferences {

    public static final String STORAGE_NAME = "VIDME";
    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init(Context cntxt) {
        context = cntxt;
    }

    private static void init() {
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void setToken(String token) {
        if (preferences == null) {
            init();
        }
        editor.putString("token", token);
        editor.commit();
    }

    public static String getToken() {
        if (preferences == null) {
            init();
        }
        return preferences.getString("token", null);
    }
}