package com.example.analogue_clock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class RegPoly {
    // ivars
    // r = radius
    private int n;
    private float x0, y0, r;
    private float x[], y[];

    private Canvas canvas; private Paint paint;

    // constructor
    public RegPoly(int n, float x0, float y0, float r, Canvas canvas, Paint paint) {
        this.n = n;
        this.x0 = x0; this.y0 = y0;
        this.r = r;

        this.canvas = canvas;
        this.paint = paint;

        // calculate x[] and y[]
        this.x = new float[this.n];this.y = new float[this.n];
        for(int i=0;i<n;i++){
            this.x[i] = (float) (x0 + r * Math.cos(2*Math.PI*i/n));
            this.y[i] = (float) (y0 + r * Math.sin(2*Math.PI*i/n));

        }
    }

    // getters
    public float getX(int i){return this.x[i%this.n];}
    public float getY(int i){return this.y[i%this.n];}

    // draw-ers
    public void drawRadius(int i){
        this.canvas.drawLine(this.x0, this.y0, this.getX(i), this.getY(i), this.paint);
    }

    // Draw points
    public void drawNodes(){
        for(int i=0;i<this.n;i++){
            this.canvas.drawCircle(this.getX(i), this.getY(i), 4, this.paint);
        }
    }

    public void drawNumbers(){
        for(int i = 1; i <= this.n; i++){
            String number = String.valueOf(i);
            this.canvas.drawText(number, this.getX(((i + 9))), this.getY(((i + 9))), this.paint);
        }
    }

    public void drawArc(int i){
        RectF oval = new RectF(x0-r+20, y0-r+20, x0+r-20, y0+r-20);
        canvas.drawArc(oval, -90, (360/60)*i, true, paint);
    }
}

