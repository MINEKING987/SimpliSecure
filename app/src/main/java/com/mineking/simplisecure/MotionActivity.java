package com.mineking.simplisecure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mineking.simplisecure.motiondetection.MotionDetector;
import com.mineking.simplisecure.motiondetection.MotionDetectorCallback;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class MotionActivity extends AppCompatActivity {
    TextView txtStatus;
    MotionDetector motionDetector;
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    50); }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    51); }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    52); }

        motionDetector = new MotionDetector(MotionActivity.this, (SurfaceView) findViewById(R.id.SurfaceView));
        txtStatus = findViewById(R.id.dum);
        txtStatus.setMovementMethod(new ScrollingMovementMethod());
        txtStatus.setText(String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis())+": Starting Service"+"\n");
        motionDetector.setLeniency(20);
        motionDetector.setMotionDetectorCallback(new MotionDetectorCallback() {
            @Override
            public void onMotionDetected() {
                txtStatus.append(String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis())+": Movement Detected"+"\n");
                Log.i("output", "moved");

            }

            @Override
            public void onSaved() {
                txtStatus.append(String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis())+": Face Detected; Image saved"+"\n");
            }

            @Override
            public void onTooDark() {
                txtStatus.append(String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis())+": It is too dark"+"\n");
                motionDetector.setCheckInterval(10000);
                Log.i("output","too dark");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtStatus.append(String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis())+": Checking again.."+"\n");
                        motionDetector.setCheckInterval(500);
                    }
                },10*1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        motionDetector.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        motionDetector.onResume();
    }

    public void Settings(View view) {
    }
}