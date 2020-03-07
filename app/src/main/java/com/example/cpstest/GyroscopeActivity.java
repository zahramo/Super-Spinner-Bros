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
    private int ball1Speed;
    private int ball2Speed;

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
        ball1 = new Ball(ball1X, ball1Y);
        ball2 = new Ball(ball2X, ball2Y);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        ball1View = findViewById(R.id.ballViewGyro);
        ball2View = findViewById(R.id.ball2ViewGyro);

        ball1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Start","Start of the game!");
                start = true;
                ball1 = new Ball(0, 0);
            }
        });
        drawBall(ball1,ball1View);
        drawBall(ball2,ball2View);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(start) {
            ball1.moveTo(event.values[0], event.values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setBallsData(){
        ball1X = Integer.parseInt(getIntent().getStringExtra("ball1X"));
        System.out.println(ball1X);
        ball1Y = Integer.parseInt(getIntent().getStringExtra("ball1Y"));
        System.out.println(ball1Y);
        ball1Speed = Integer.parseInt(getIntent().getStringExtra("ball1Speed"));
        System.out.println(ball1Speed);

        ball2X = Integer.parseInt(getIntent().getStringExtra("ball2X"));
        System.out.println(ball2X);
        ball2Y = Integer.parseInt(getIntent().getStringExtra("ball2Y"));
        System.out.println(ball2Y);
        ball2Speed = Integer.parseInt(getIntent().getStringExtra("ball2Speed"));
        System.out.println(ball2Speed);
    }

    private void drawBall(Ball ball, BallView ballView){
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ballView.render(ball.getX(), ball.getY());
            }
        }, 0, 17); // TODO: 3/7/2020 : check the period of timer is proper or not.
    }

}
