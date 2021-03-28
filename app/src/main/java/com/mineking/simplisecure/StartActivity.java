package com.mineking.simplisecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }

    public void startMotion(View view) {
        Intent intent = new Intent(this,MotionActivity.class);
        startActivity(intent);
    }

    public void startesp(View view) {
        Intent intent = new Intent(this,ESP_Activity.class);
        startActivity(intent);
    }
}