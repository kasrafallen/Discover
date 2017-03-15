package net.puzzleco.healthdiscovery.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Util {

    private static final String PREF_WIDTH = "PREF_WIDTH";
    private static final String PREF_HEIGHT = "PREF_HEIGHT";
    private static final String PREF_SIGNED = "PREF_SIGNED";
    private Context context;

    public Util(Context context) {
        this.context = context;
    }

    public boolean isDimen() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.contains(PREF_HEIGHT);
    }

    public float[] getDimen() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        float height = preferences.getFloat(PREF_HEIGHT, 0);
        float width = preferences.getFloat(PREF_WIDTH, 0);
        return new float[]{width, height};
    }

    public void setDimen(float[] dimens) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(PREF_WIDTH, dimens[0]);
        editor.putFloat(PREF_HEIGHT, dimens[1]);
        editor.apply();
    }

    public boolean isSigned() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PREF_SIGNED, false);
    }

    public void setSigned() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(PREF_SIGNED, true).apply();
    }
}
