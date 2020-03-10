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
        //this.t0 = System.currentTimeMillis();
    }

    public void setT0() {
        this.t0 = System.currentTimeMillis();
    }

    private void setDisplaySize(int width, int height)
    {
        this.displayWidth = width;
        this.displayHeight = height;
    }

    public void move() {
        System.out.println("in move function");

        t = System.currentTimeMillis();
        float deltaT = (float) (t-t0) / 1000;
        float deltaX = (float) 0.5*ax*deltaT*deltaT + vx*deltaT;
        float deltaY = (float) 0.5*ay*deltaT*deltaT + vy*deltaT;

        //System.out.println("delta x = "+ deltaX + "delta y = " + deltaY + "delta t = " + deltaT );
        System.out.println("delta y = " + deltaY + ", ay = " + ay + ", vy = " + vy );

        if (this.x + deltaX > displayWidth/2 - r ||
                this.x + deltaX < -displayWidth/2 + r) {
          //  ax = -ax;
            vx = -vx;
        }else{
            x += deltaX;
            vx += ax*deltaT;
        }
        if (this.y + deltaY> displayHeight/2 - r ||
                this.y + deltaY < -displayHeight/2 + r) {
          //  ay = -ay;
            vy = -vy;
        }
        else{
            y += deltaY;
            vy += ay*deltaT;
        }
        t0 = t;

        System.out.println("x = " + x + "y = " + y);
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
        System.out.println("in draw");
        //move();
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
        float Fs = Fn * Config.COF_S;
        float Fkx = (Fx > 0) ? -Fn * Config.COF_K : Fn * Config.COF_K;
        float Fky = (Fy > 0) ? -Fn * Config.COF_K : Fn * Config.COF_K;

        System.out.println("Fn = " + Fn + " Fx = " + Fx + " Fy = " + Fy + " Fs = " + Fs + " Fkx = " + Fkx + " Fky = " + Fky);
        System.out.println("gn = " + gz + " gx = " + gx + " gy = " + gy);

        if(Math.abs(Fs) < Math.abs(Fx)){
            ax = (Fx + Fkx)/m;
            System.out.println(1);

        }else{
           // ax = 0;
            vx = 0;
            System.out.println(2);
        }
        if(Math.abs(Fs) < Math.abs(Fy)){
            ay = (Fy + Fky)/m;
            System.out.println(3);
        }else{
            vy = 0;
            System.out.println(4);
        }
        t0 = t;
        //move();
    }

}



