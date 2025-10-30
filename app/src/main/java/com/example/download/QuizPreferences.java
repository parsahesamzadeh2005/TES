package com.example.download;

import android.content.Context;
import android.content.SharedPreferences;

public class QuizPreferences {
    private static final String PREF_NAME = "quiz_preferences";
    private static final String KEY_Q1 = "q1";
    private static final String KEY_Q2 = "q2";
    private static final String KEY_Q3 = "q3";

    private final SharedPreferences prefs;

    public QuizPreferences(Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAnswer(int questionNumber, String value) {
        SharedPreferences.Editor e = prefs.edit();
        if (questionNumber == 1) e.putString(KEY_Q1, value);
        else if (questionNumber == 2) e.putString(KEY_Q2, value);
        else if (questionNumber == 3) e.putString(KEY_Q3, value);
        e.apply();
    }

    public String getAnswer(int questionNumber) {
        if (questionNumber == 1) return prefs.getString(KEY_Q1, "");
        if (questionNumber == 2) return prefs.getString(KEY_Q2, "");
        if (questionNumber == 3) return prefs.getString(KEY_Q3, "");
        return "";
    }

    public void clearAll() {
        prefs.edit().clear().apply();
    }

    public String calculateResult() {
        int sport = 0, music = 0, movie = 0, science = 0;
        String q1 = getAnswer(1);
        String q2 = getAnswer(2);
        String q3 = getAnswer(3);

        if ("sport".equals(q1)) sport++; else if ("music".equals(q1)) music++; else if ("movie".equals(q1)) movie++; else if ("science".equals(q1)) science++;
        if ("sport".equals(q2)) sport++; else if ("music".equals(q2)) music++; else if ("movie".equals(q2)) movie++; else if ("science".equals(q2)) science++;
        if ("sport".equals(q3)) sport++; else if ("music".equals(q3)) music++; else if ("movie".equals(q3)) movie++; else if ("science".equals(q3)) science++;

        if (sport >= music && sport >= movie && sport >= science) return "sport";
        if (music >= movie && music >= science) return "music";
        if (movie >= science) return "movie";
        return "science";
    }

    public String getResultText(String key) {
        switch (key) {
            case "sport": return "Sport";
            case "music": return "Music";
            case "movie": return "Movie";
            case "science": return "Science";
            default: return "Unknown";
        }
    }

    public String getDownloadUrl(String key) {
        // Public sample files per category; replace with your own URLs if desired.
        switch (key) {
            case "sport":
                return "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
            case "music":
                return "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";
            case "movie":
                return "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
            case "science":
                return "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-zip-file.zip";
            default:
                return "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
        }
    }
}