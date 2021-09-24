package us.game3;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScore {
    public static final String PREFS_NAME = "HighScore";
    private static final int DEFAULT_SCORE = 0;
    private static final String HIGH_SCORE = "HIGH_SCORE";

    public static int getHighScore(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(HIGH_SCORE, DEFAULT_SCORE);
    }

    public static void setHighScore(Context context, int score) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(HIGH_SCORE, score);
        editor.apply();
    }
}