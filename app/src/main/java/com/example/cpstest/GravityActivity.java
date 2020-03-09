package com.example.cpstest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

public class GravityActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    private int ball1X;
    private int ball2X;
    private int ball1Y;
    private int ball2Y;
    private int ball1Vx;
    private int ball1Vy;
    private int ball2Vx;
    private int ball2Vy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);
        setBallsData();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void setBallsData(){
        ball1X = Integer.parseInt(getIntent().getStringExtra("ball1X"));
        System.out.println(ball1X);
        ball1Y = Integer.parseInt(getIntent().getStringExtra("ball1Y"));
        System.out.println(ball1Y);
        ball1Vx = Integer.parseInt(getIntent().getStringExtra("ball1Vx"));
        System.out.println(ball1Vx);
        ball1Vy = Integer.parseInt(getIntent().getStringExtra("ball1Vy"));
        System.out.println(ball1Vy);

        ball2X = Integer.parseInt(getIntent().getStringExtra("ball2X"));
        System.out.println(ball2X);
        ball2Y = Integer.parseInt(getIntent().getStringExtra("ball2Y"));
        System.out.println(ball2Y);
        ball2Vx = Integer.parseInt(getIntent().getStringExtra("ball2Vx"));
        System.out.println(ball2Vx);
        ball2Vy = Integer.parseInt(getIntent().getStringExtra("ball2Vy"));
        System.out.println(ball2Vy);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        System.out.println("x:" + event.values[0]+" Y:" + event.values[1] + " z:" + event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
