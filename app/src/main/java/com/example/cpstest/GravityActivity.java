package com.example.cpstest;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

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

    private int screenWidth;
    private int screenHeight;

    private boolean start = false;
    private boolean running = false;

    private Ball ball1;
    private Ball ball2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);
        setBallsData();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        ball1 = new Ball(ball1X, ball1Y, ball1Vx, ball1Vy, Config.FIRST_BALL_MASS, width, height);
        ball2 = new Ball(ball2X, ball2Y, ball2Vx, ball2Vy, Config.SECOND_BALL_MASS, width, height);
        ball1.setView(findViewById(R.id.ball1ViewGravity));
        ball2.setView(findViewById(R.id.ball2ViewGravity));

        ball1.getView().render(ball1X,ball1Y);
        ball2.getView().render(ball2X,ball2Y);
        ball1.getView().setOnClickListener(view -> start = true);
        ball2.getView().setOnClickListener(view -> start = true);

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start) {
                    if(!running){
                        ball1.setT0();
                        ball2.setT0();
                        running = true;
                    }
                    ball1.move();
                    ball2.move();
                    checkCollision();
                    ball1.draw();
                    ball2.draw();
                }
            }
        }, 0, 1);
    }

    private void setBallsData(){
        ball1X = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball1X")));
        ball1Y = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball1Y")));
        ball1Vx = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball1Vx")));
        ball1Vy = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball1Vy")));

        ball2X = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball2X")));
        ball2Y = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball2Y")));
        ball2Vx = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball2Vx")));
        ball2Vy = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("ball2Vy")));
    }

    private void checkCollision() {
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

            int base = 1000;

            dis1 = (float) Math.pow((x1 + newVx1 / base) - (x2 + newVx2 / base), 2);
            dis2 = (float) Math.pow((y1 + newVy1 / base) - (y2 + newVy2 / base), 2);
            distance = (float) Math.sqrt(dis1 + dis2);
            while (distance <= Config.BALL_SIZE) {
                x1 = ball1.getX();
                y1 = ball1.getY();
                x2 = ball2.getX();
                y2 = ball2.getY();
                ball1.setX(x1 + newVx1 / base);
                ball1.setY(y1 + newVy1 / base);
                ball2.setX(x2 + newVx2 / base);
                ball2.setY(y2 + newVy2 / base);
                dis1 = (float) Math.pow((x1 + newVx1 / base) - (x2 + newVx2 / base), 2);
                dis2 = (float) Math.pow((y1 + newVy1 / base) - (y2 + newVy2 / base), 2);
                distance = (float) Math.sqrt(dis1 + dis2);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        System.out.println("x:" + event.values[0]+" Y:" + event.values[1] + " z:" + event.values[2]);
        if(start){
            //checkCollision();
            ball1.handleGravity(event.values[0], event.values[1], event.values[2]);
            //checkCollision();
            ball2.handleGravity(event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
