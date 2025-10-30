package com.example.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView txtResult;
    private Button btnDownload;
    private QuizPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResult = findViewById(R.id.txtResult);
        btnDownload = findViewById(R.id.btnDownload);
        prefs = new QuizPreferences(this);

        String resultKey = prefs.calculateResult();
        String resultText = prefs.getResultText(resultKey);
        txtResult.setText("Top choice: " + resultText);

        btnDownload.setOnClickListener(v -> {
            String url = prefs.getDownloadUrl(resultKey);
            startDownload(url, resultKey);
        });
    }

    private void startDownload(String url, String key) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("Downloading related file");
            request.setDescription("Downloading...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "quiz_" + key);

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            if (manager != null) manager.enqueue(request);
        } catch (Exception ignored) {
        }
    }
}