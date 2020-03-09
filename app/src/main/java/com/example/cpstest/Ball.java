package com.example.cpstest;

import android.util.DisplayMetrics;

import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private BallView view;
    private float x0;
    private float y0;
    private float x;
    private float y;
    private float vx0;
    private float vy0;
    private float vx;
    private float vy;
    private float ax;
    private float ay;
    private float m;
    private float displayWidth;
    private float displayHeight;
    private float r;

    public Ball(float x0,float y0, float vx0, float vy0, float m, int width, int height){
        this.x0 = x0;
        this.y0 = y0;
        this.vx0 = vx0;
        this.vy0 = vy0;

        this.x = this.x0;
        this.y = this.y0;
        this.vx = this.vx0;
        this.vy = this.vy0;
        this.r = (float) (Config.BALL_SIZE/2);
        this.m = m;
        setDisplaySize(width, height);
    }

    public void setDisplaySize(int width, int height)
    {
        this.displayWidth = width;
        this.displayHeight = height;
    }

    public void move() {
        if (this.x + this.vx > displayWidth/2 - Config.BALL_SIZE/2 || this.x + this.vx < -displayWidth/2 + Config.BALL_SIZE/2)
            this.vx = -this.vx;
        if (this.y + this.vy > displayHeight/2 - Config.BALL_SIZE/2 || this.y + this.vy < -displayHeight/2 + Config.BALL_SIZE/2)
            this.vy = -this.vy;

        this.x += this.vx;
        this.y += this.vy;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public float getVx() {
        return vx;
    }
    public float getVy() {
        return vy;
    }

    public BallView getView() {
        return view;
    }

    public void setView(BallView view) {
        this.view = view;
    }

    public void draw(){
        move();
        view.render(x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}

