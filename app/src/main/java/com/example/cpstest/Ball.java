package com.example.cpstest;

import android.util.DisplayMetrics;

import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private BallView view;
    private float x;
    private float y;
    private float vx;
    private float vy;
    private float ax;
    private float ay;
    private float m;
    private float displayWidth;
    private float displayHeight;
    private float r;

    private long t0;
    private long t;

    Ball(float x0, float y0, float vx0, float vy0, float m, int width, int height){
        this.x = x0;
        this.y = y0;
        this.vx = vx0;
        this.vy = vy0;
        this.ax = 0;
        this.ay = 0;
        this.r = (float) (Config.BALL_SIZE/2);
        this.m = m;
        setDisplaySize(width, height);
        t0 = System.currentTimeMillis();
    }

    private void setDisplaySize(int width, int height)
    {
        this.displayWidth = width;
        this.displayHeight = height;
    }

    private void move() {
        t = System.currentTimeMillis();
        float deltaT = (float) (t-t0) / 1000;

        if (this.x + this.vx > displayWidth/2 - Config.BALL_SIZE/2 ||
                this.x + this.vx < -displayWidth/2 + Config.BALL_SIZE/2) {
            ax = 0;
            vx = 0;
        }
        if (this.y + this.vy > displayHeight/2 - Config.BALL_SIZE/2 ||
                this.y + this.vy < -displayHeight/2 + Config.BALL_SIZE/2) {
            ay = 0;
            vy = 0;
        }
//
        x += (float) 0.5*ax*deltaT*deltaT + vx*deltaT;
        vx += ax*deltaT;
        y += (float) 0.5*ay*deltaT*deltaT + vy*deltaT;
        vy += ay*deltaT;
        t0 = t;
    }

    float getX() {
        return x;
    }
    float getY() {
        return y;
    }

    float getVx() {
        return vx;
    }
    float getVy() {
        return vy;
    }

    BallView getView() {
        return view;
    }

    void setView(BallView view) {
        this.view = view;
    }

    void draw(){
        move();
        view.render(x, y);
    }

    void setX(float x) {
        this.x = x;
    }
    void setY(float y) {
        this.y = y;
    }

    void setVx(float vx) {
        this.vx = vx;
    }
    void setVy(float vy) {
        this.vy = vy;
    }

    public float getR() {
        return r;
    }
    public void setR(float r) {
        this.r = r;
    }

    void gravityUpdate(float gx, float gy, float gz){
        t = System.currentTimeMillis();
        float deltaT = (float) (t-t0) / 1000;

        gx = -gx;
        float Fn = gz * m * Config.METER;
        float Fx = gx * m * Config.METER;
        float Fy = gy * m * Config.METER;

        System.out.println("GX:"+gx+" GY:"+gy+" GZ:"+gz);

        float Fs = Fn * Config.COF_S;
        if(Math.abs(Fs) < Math.abs(Fx)){
            float Fk = (Fx > 0) ? -Fn * Config.COF_K : Fn * Config.COF_K;
            ax = (Fx + Fk)/m;
            x += (float) 0.5*ax*deltaT*deltaT + vx*deltaT;
            vx += ax*deltaT;

            System.out.println("x "+ x + " vx: " + vx + " ax: " + ax);
//            System.out.println("y: "+ y + " vy: " + vy + " ay: " + ay);
        }else{
            vx = 0;
        }
        if(Math.abs(Fs) < Math.abs(Fy)){
            float Fk = (Fy > 0) ? -Fn * Config.COF_K : Fn * Config.COF_K;
            ay = (Fy + Fk)/m;
            y += (float) 0.5*ay*deltaT*deltaT + vy*deltaT;
            vy += ay*deltaT;
        }else{
            vy = 0;
        }
        t0 = t;
    }
}

