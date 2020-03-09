package com.example.cpstest;

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

    private float r;

    public Ball(float x0,float y0, float vx0, float vy0, float m){
        this.x0 = x0;
        this.y0 = y0;
        this.vx0 = vx0;
        this.vy0 = vy0;

        this.x = this.x0;
        this.y = this.y0;
        this.vx = this.vx0;
        this.vy = this.vy0;

        this.m = m;
        this.r = 80;
    }

    public void move() {
        this.x += this.vx;
        this.y += this.vy;
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
        move();
        view.render(x, y);
    }

    public boolean isOkToMove(int scWidth, int scHeight){
        if(x+r+vx > scWidth/2) return false;
        if(x-r+vx < -1*scWidth/2) return false;
        if(y+r+vy > scHeight/2) return false;
        if(y-r+vy < -1*scHeight/2) return false;
        return true;
    }

    public void configMove(int scWidth, int scHeight){

            if(x+r == scWidth/2 || x-r == -scWidth/2){
                vx = -vx;
                System.out.println(1);
            }else if(x+r+vx > scWidth/2){
                x -= x+r+vx - scWidth/2;
            } else if(x-r+vx < -1*scWidth/2){
                x += -scWidth/2 - (x-r+vx);
            }
            if(y+r == scHeight/2 || y-r == -scHeight/2){
                vy= -vy;
            }
            else if(y+r+vy > scHeight/2){
                y -= y+r+vy - scHeight/2;
            } else if(y-r+vy < -1*scHeight/2) {
                y += -scHeight / 2 - (y - r + vy);
            }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}

