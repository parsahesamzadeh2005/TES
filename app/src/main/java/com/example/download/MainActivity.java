package com.example.download;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup q1, q2, q3;
    Button btnResult;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //  q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        btnResult = findViewById(R.id.btnResult);

        btnResult.setOnClickListener(v -> showResult());
    }

    private void showResult() {
        int[] selections = {
                q1.getCheckedRadioButtonId(),
                q2.getCheckedRadioButtonId(),
                q3.getCheckedRadioButtonId()
        };

        if (selections[0] == -1 || selections[1] == -1 || selections[2] == -1) {
            Toast.makeText(this, "لطفاً به همه‌ی سؤال‌ها جواب بده", Toast.LENGTH_SHORT).show();
            return;
        }
        int sport = 0, music = 0, movie = 0, science = 0;

        for (int id : selections) {
            if (id == R.id.q1_sport || id == R.id.q2_sport || id == R.id.q3_sport) sport++;
            if (id == R.id.q1_music || id == R.id.q2_music || id == R.id.q3_music) music++;
            if (id == R.id.q1_movie || id == R.id.q2_movie || id == R.id.q3_movie) movie++;
            if (id == R.id.q1_science || id == R.id.q2_science || id == R.id.q3_science) science++;
        }
        String topic;
        String url;

        if (sport >= music && sport >= movie && sport >= science) {
            topic = "ورزشی";
            url = "https://caspian14.cdn.asset.aparat.com/aparat-video/cecc5e36dd0af3b0e71f22ceb7f55e7559287628-360p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjFkM2YzZTk5MTgxYzk1NWY2ZTgwNzg4NTk1NDA2ZjM2IiwiZXhwIjoxNzYxMjMwMDk3LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.Wibt4XDnhf0k8tfZVjk5tgK0krcn-dJgOWpM7Y_7LuU";
        } else if (music >= movie && music >= science) {
            topic = "موسیقی";
            url = "https://caspian16.cdn.asset.aparat.com/aparat-video/81b0bf504b4a5b340c7f1e2963bdf15b65215641-720p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjY3OTg2OGVhMzMyYTc4YzdlYzc4ZTQ1MjA4NGQ4YTdmIiwiZXhwIjoxNzYxMjMwMTY4LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.6BOUJOL8wqVH82bfPiTlnW9J9qN6KPxvqfwrrLMnvGU";
        } else if (movie >= science) {
            topic = "فیلم";
            url = "https://aspb1.cdn.asset.aparat.com/aparat-video/4d544273ed6e023b2a3fdd7b3f38b96118048454-144p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjUzYjcwMWYyNzg4NjY2NTA4YjgyNWE3MzgzMzRmNmJhIiwiZXhwIjoxNzYxMjMwMTA1LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.byC5LnqJier-oau-QOidPezRUmZ_P77H0oCbYiaS7Ps";
        } else {
            topic = "علمی";
            url = "https://caspian21.cdn.asset.aparat.com/aparat-video/2a1779997ad2cfdcf7d71ffe079b7b9666485230-240p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImY5OTZmZGE1ZmQ5OWE5ZGQ2MTZiMDNhNjExYjFiYjFkIiwiZXhwIjoxNzYxMjMwMTE1LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.pkTIWkGDfjOUA214PYbEk_ofTKtT0kIopNi6xTVdBbQ";
        }

        Toast.makeText(this, "موضوع مورد علاقه‌ات: " + topic, Toast.LENGTH_LONG).show();
        startDownload(url);

    }
    private void startDownload(String url) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("دانلود فایل مرتبط");
            request.setDescription("در حال دانلود...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "interest_file");

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

        } catch (Exception e) {
            Toast.makeText(this, "خطا در دانلود!", Toast.LENGTH_SHORT).show();
        }

    }
}