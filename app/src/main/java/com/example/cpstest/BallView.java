package com.example.cpstest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BallView extends View {
    private float x = 0;
    private float y = 0;

    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float initX = canvas.getWidth() / 2;
        float initY = canvas.getHeight() / 2;
        float x = this.x + initX;
        float y = this.y + initY;
        float ballSize = (float) (Config.BALL_SIZE);

        Drawable ballPic = getResources().getDrawable(R.drawable.ic_ball);
        ballPic.setBounds( (int)(x - ballSize/2), (int)(y - ballSize/2) ,(int)(x + ballSize/2), (int)(y + ballSize/2));
        ballPic.draw(canvas);
    }

    public void render(float x, float y)
    {
        this.x = x;
        this.y = y;
        postInvalidate();
    }
}
