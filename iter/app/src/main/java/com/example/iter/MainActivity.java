package com.example.iter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Life: -", "A activity está sendo criada");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Life: -", "A activity está prestes a se tornar visível.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Life: -", "A activity está visível.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Life: -", "A activity está pausada.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Life: -", "A activity não está mais visível, mas permanece em memória");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Life: -", "A activity está prestes a ser removida da memória.");
    }
}
