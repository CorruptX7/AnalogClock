package com.example.analogue_clock; // https://pastebin.com/trKKeRbA

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TimerSurfaceView extends SurfaceView implements Runnable {

    int secs;

    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;
    private boolean flag = false;
    private int clockColor;

    // Constructor
    public TimerSurfaceView(Context context, float length, int secs) {
        super(context);

        this.length = length;
        this.secs = secs;
        this.holder = getHolder();
    }

    public void onResumeTimerSurfaceView() {
        thread = new Thread(this);
        thread.start();

        running = true;
    }

    public void onPauseTimerSurfaceView() {
        running = false;
        boolean reentry = true;

        while(reentry) {
            try {
                thread.join();
                reentry = false;
            } catch (Exception e) {

            }
        }
    }

    public void onDestroyTimerSurfaceView() {
        running = false;
    }

    public void changeColor(int color){
        flag = true;
        clockColor = color;
    }

    @Override
    public void run() {
        int sec = 0;

        while (running) {
            if(holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();

                // In here have your own drawing
                Paint paint = new Paint(); paint.setColor(Color.WHITE);

                // Comment this out - Hint for creating an arc (To fill the whole circle) - Work with 60000 points to fill the circle
                // Figure out the delay
                 canvas.drawPaint(paint);

                // Draw the marks
                // 2 RegPoly objects for hour marks and sec marks
                paint.setColor(Color.BLACK);

                if(flag) {
                    paint.setColor(clockColor);
                }

                RegPoly secMarks = new RegPoly(60,getWidth()/2, getHeight()/2, length, canvas, paint);
//                RegPoly secondHand = new RegPoly(60,getWidth()/2, getHeight()/2, length-20, canvas, paint);
                RegPoly arc = new RegPoly(60,getWidth()/2, getHeight()/2, length, canvas, paint);

                secMarks.drawNodes();
//                secondHand.drawRadius(sec + 45);

                // Draw an arc
                 arc.drawArc(sec);

                // Sleep for 1 second
                try {
                    Thread.sleep(1000 * secs / 120);

                } catch (Exception e) {

                }

                sec = sec + 1;

                if (sec == 61) {
                    running = false;
                }

                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}