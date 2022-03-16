package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String[] golfers = getIntent().getExtras().getStringArray("golfers");
        int holeCount = getIntent().getExtras().getInt("holeCount");
        String courseName = getIntent().getExtras().getString("courseName");
        int[] result = getIntent().getExtras().getIntArray("results");
        // Konnte ich leider nur eindimensional übermitteln, sollte aber kein Problem sein (;
        // results[y * spielerAnzahl + x] == results[y][x]
    }
}