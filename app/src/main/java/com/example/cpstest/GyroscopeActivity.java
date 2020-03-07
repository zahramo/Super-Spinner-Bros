package com.example.cpstest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    private Ball ball;
    private boolean start = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        ball = new Ball(0, 0);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        BallView ballView = findViewById(R.id.ballViewGyro);
        ballView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Start","Start of the game!");
                start = true;
                ball = new Ball(0, 0);
            }
        });

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                BallView ballView = findViewById(R.id.ballViewGyro);
                ballView.render(ball.getX(), ball.getY());
            }
        }, 0, 17); // TODO: 3/7/2020 : check the period of timer is proper or not.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(start) {
            ball.moveTo(event.values[0], event.values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
