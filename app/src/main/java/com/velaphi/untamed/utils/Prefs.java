package com.velaphi.untamed.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.velaphi.untamed.R;

public class Prefs {
    public static final String PREFS_NAME = "prefs";

    public static void saveDescription(Context context, String description) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.widget_animal_key), description);

        prefs.apply();
    }

    public static String loadDescription(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(context.getString(R.string.widget_animal_key), "");
    }
}