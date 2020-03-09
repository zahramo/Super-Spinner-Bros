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
    private int ball1X;
    private int ball2X;
    private int ball1Y;
    private int ball2Y;
    private int ball1Vx;
    private int ball1Vy;
    private int ball2Vx;
    private int ball2Vy;

    private Ball ball1;
    private Ball ball2;
    private boolean start = false;

    BallView ball1View;
    BallView ball2View;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setBallsData();
        ball1 = new Ball(ball1X, ball1Y, ball1Vx, ball1Vy, Config.FIRST_BALL_MASS);
        ball2 = new Ball(ball2X, ball2Y, ball2Vx, ball2Vy, Config.SECOND_BALL_MASS);
        ball1.setView(findViewById(R.id.ballViewGyro));
        ball2.setView(findViewById(R.id.ball2ViewGyro));

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        ball1.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = true;
            }
        });
        ball2.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = true;
            }
        });

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start) {
                    ball1.draw();
                    ball2.draw();
                }
            }
        }, 0, 17);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(start) {
//            ball1.moveTo(event.values[0] * 10, event.values[1] * 10);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setBallsData(){
        ball1X = Integer.parseInt(getIntent().getStringExtra("ball1X"));
        System.out.println("ball1X :  " + ball1X);
        ball1Y = Integer.parseInt(getIntent().getStringExtra("ball1Y"));
        System.out.println("ball1Y :  " + ball1Y);
        ball1Vx = Integer.parseInt(getIntent().getStringExtra("ball1Vx"));
        System.out.println("ball1Vx :  " + ball1Vx);
        ball1Vy = Integer.parseInt(getIntent().getStringExtra("ball1Vy"));
        System.out.println("ball1Vy :  " + ball1Vy);

        ball2X = Integer.parseInt(getIntent().getStringExtra("ball2X"));
        System.out.println("ball2X :  " + ball2X);
        ball2Y = Integer.parseInt(getIntent().getStringExtra("ball2Y"));
        System.out.println("ball2Y :  " + ball2Y);
        ball2Vx = Integer.parseInt(getIntent().getStringExtra("ball2Vx"));
        System.out.println("ball2Vx :  " + ball2Vx);
        ball2Vy = Integer.parseInt(getIntent().getStringExtra("ball2Vy"));
        System.out.println("ball2Vy :  " + ball2Vy);
    }
}
