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

        ball1.draw();
        ball2.draw();

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
                    checkCollision();
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


    private void checkCollision() { //don't touch this function please :)
        float x1 = ball1.getX();
        float y1 = ball1.getY();
        float vx1 = ball1.getVx();
        float vy1 = ball1.getVy();
        float x2 = ball2.getX();
        float y2 = ball2.getY();
        float vx2 = ball2.getVx();
        float vy2 = ball2.getVy();
        float m1 = Config.FIRST_BALL_MASS;
        float m2 = Config.SECOND_BALL_MASS;
        float dis1 = (float) Math.pow((x1) - (x2), 2);
        float dis2 = (float) Math.pow((y1) - (y2), 2);
        float distance = (float) Math.sqrt(dis1 + dis2);

        if (distance <= Config.BALL_SIZE) {
            System.out.println("vx1:  "+ vx1 + "vy1:  "+ vy1);
            System.out.println("vx2:  "+ vx2 + "vy2:  "+ vy2);
            float nx = (x2 - x1);
            float ny = (y2 - y1);

            nx = (float) (nx / Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
            ny = (float) (ny / Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));


            float tx = -ny;
            float ty = nx;
            float v1n = nx * vx1 +  ny * vy1;
            float v2n = nx * vx2 + ny * vy2;

            float v1pt = tx * vx1 + ty * vy1;
            float v2pt = tx * vx2 + ty * vy2;

            float v1pn = (v1n*(m1 - m2) + 2*m2*v2n) / (m1 + m2);
            float v2pn = (v2n*(m2 - m1) + 2*m1*v1n) / (m1 + m2);

            System.out.println("nx: "+ nx);
            System.out.println("ny: "+ ny);
            System.out.println("tx: "+ tx);
            System.out.println("ty: "+ ty);

            float v1pnx = v1pn * nx;
            float v1pny = v1pn * ny;
            float v2pnx = v2pn * nx;
            float v2pny = v2pn * ny;

            float v1ptx = v1pt * tx;
            float v1pty = v1pt * ty;
            float v2ptx = v2pt * tx;
            float v2pty = v2pt * ty;

            float newVx1 = v1ptx + v1pnx;
            float newVy1 = v1pty + v1pny;
            float newVx2 = v2ptx + v2pnx;
            float newVy2 = v2pty + v2pny;



            ball1.setVx(newVx1);
            ball1.setVy(newVy1);
            ball2.setVx(newVx2);
            ball2.setVy(newVy2);

            dis1 = (float) Math.pow((x1 + newVx1) - (x2 + newVx2), 2);
            dis2 = (float) Math.pow((y1 + newVy1) - (y2 + newVy2), 2);
            distance = (float) Math.sqrt(dis1 + dis2);
            while (distance <= Config.BALL_SIZE) {
                x1 = ball1.getX();
                y1 = ball1.getY();
                x2 = ball2.getX();
                y2 = ball2.getY();
                ball1.setX(x1 + newVx1);
                ball1.setY(y1 + newVy1);
                ball2.setX(x2 + newVx2);
                ball2.setY(y2 + newVy2);
                dis1 = (float) Math.pow((x1 + newVx1) - (x2 + newVx2), 2);
                dis2 = (float) Math.pow((y1 + newVy1) - (y2 + newVy2), 2);
                distance = (float) Math.sqrt(dis1 + dis2);
            }
        }

    }
}
