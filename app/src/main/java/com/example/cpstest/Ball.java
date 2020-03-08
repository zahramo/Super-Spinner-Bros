package com.example.cpstest;

import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private BallView view;
    private float x0;
    private float y0;
    private float x;
    private float y;
    private float v0;
    private float v;
    private float a;
    private float m;

    public Ball(float x0,float y0, float v0, float m){
        this.x0 = x0;
        this.y0 = y0;
        this.v0 = v0;

        this.x = this.x0;
        this.y = this.y0;
        this.v = this.v0;

        this.m = m;
    }

    //This is a test function and can be deleted or be replaced
    //Usage : in GyroscopeActivity.java in onSensorChanged function.
    public void moveTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public BallView getView() {
        return view;
    }

    public void setView(BallView view) {
        this.view = view;
    }

    public void draw(){
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.render(x, y);
            }
        }, 0, 17); // TODO: 3/7/2020 : check the period of timer is proper or not.
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}

