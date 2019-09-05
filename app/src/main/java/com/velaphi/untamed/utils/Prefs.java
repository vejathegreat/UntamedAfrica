package com.velaphi.untamed.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.Fact;

public class Prefs {
    public static final String PREFS_NAME = "prefs";

    public static void saveFact(Context context, Fact fact) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.widget_title_key), fact.getTitle());
        prefs.putString(context.getString(R.string.widget_fact_key), fact.getDescription());

        prefs.apply();
    }

    public static String loadDescription(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(context.getString(R.string.widget_fact_key), "");
    }

    public static String loadTitle(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(context.getString(R.string.widget_title_key), "");
    }
}