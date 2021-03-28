package com.mineking.simplisecure.motiondetection;

public interface MotionDetectorCallback {
    void onMotionDetected();
    void onSaved();
    void onTooDark();
}
