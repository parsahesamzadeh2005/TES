package com.example.download;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Question2Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button btnNext;
    private QuizPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);
        prefs = new QuizPreferences(this);

        btnNext.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }
            String answer = idToAnswer(selectedId);
            prefs.saveAnswer(2, answer);
            startActivity(new Intent(this, Question3Activity.class));
        });
    }

    private String idToAnswer(int id) {
        if (id == R.id.radio_sport) return "sport";
        if (id == R.id.radio_music) return "music";
        if (id == R.id.radio_movie) return "movie";
        return "science";
    }
}