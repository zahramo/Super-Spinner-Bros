package com.example.cpstest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        ball1 = new Ball(ball1X, ball1Y, ball1Vx, ball1Vy, Config.FIRST_BALL_MASS, width, height);
        ball2 = new Ball(ball2X, ball2Y, ball2Vx, ball2Vy, Config.SECOND_BALL_MASS, width, height);
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
//                    checkCollision();
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

//    private void checkCollision() { //don't touch this function please :)
//        float x1 = ball1.getX();
//        float y1 = ball1.getY();
//        float vx1 = ball1.getVx();
//        float vy1 = ball1.getVy();
//        float x2 = ball2.getX();
//        float y2 = ball2.getY();
//        float vx2 = ball2.getVx();
//        float vy2 = ball2.getVy();
//        float m1 = Config.FIRST_BALL_MASS;
//        float m2 = Config.SECOND_BALL_MASS;
//        float radius = (float) (Config.BALL_SIZE / 2);
//        boolean isCollision = false;
//        float distance = (float) Math.sqrt(Math.pow((x1 + vx1) - (x2 ), 2) + Math.pow(y1-y2, 2));
////        if ((y1 + vy1 + radius >= y2 + vy2 - radius || y1 + vy1 - radius <= y2 + vy2 + radius) && Math.abs(x1 - x2) < radius * 2)
////            isCollision = true;
////            float newVx1 = (vx1*(m1 - m2) + 2*m2*vx2) / (m1 + m2);
////            float newVy1 = (vy1*(m1 - m2) + 2*m2*vy2) / (m1 + m2);
////            float newVx2 = (vx2*(m2 - m1) + 2*m1*vx1) / (m1 + m2);
////            float newVy2 = (vy2*(m2 - m1) + 2*m1*vy1) / (m1 + m2);
//        if (distance <=) {
//            float newVx1 = (-vx1);
//            float newVy1 = (-vy1);
//            float newVx2 = (-vx2);
//            float newVy2 = (-vy2);
//            ball1.setVx(newVx1);
//            ball1.setVy(newVy1);
//            ball2.setVx(newVx2);
//            ball2.setVy(newVy2);
//        }
//    }
}
