package com.example.cpstest;

public class Ball {
    private float x0;
    private float y0;
    private float x;
    private float y;

    public Ball(float x,float y){
        this.x = x;
        this.y = y;
    }

    //This is a test function and can be deleted or be replaced
    //Usage : in GyroscopeActivity.java in onSensorChanged function.
    public void moveTo(float x, float y){
        this.x = x*10;
        this.y = y*10;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}

