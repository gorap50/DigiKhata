package com.example.digikhata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class firstActivity extends AppCompatActivity {
    private static final long SPLASH_TIME = 12000;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent=new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
                finish();
            }
        },SPLASH_TIME);
    }
}