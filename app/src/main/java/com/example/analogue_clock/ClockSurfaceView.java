package com.example.analogue_clock; // https://pastebin.com/hLPnyzjV

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class ClockSurfaceView extends SurfaceView implements Runnable {

    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;
    private boolean flag = false;
    private int clockColor;

    // Constructor
    public ClockSurfaceView(Context context, float length) {
        super(context);

        this.length = length;
        this.holder = getHolder();
    }

    public void onResumeClockSurfaceView() {
        thread = new Thread(this);
        thread.start();

        running = true;
    }

    public void onPauseClockSurfaceView() {
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

    public void onDestroyClockSurfaceView() {
        running = false;
    }

    public void changeColor(int color){
        flag = true;
        clockColor = color;
    }

    @Override
    public void run() {
        int sec = 0;
        int hour = 0;
        int minute = 0;
        int mSec = 0;

        while (running) {
            if(holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();

                // In here have your own drawing
                Paint paint = new Paint(); paint.setColor(Color.WHITE);
                canvas.drawPaint(paint);

                // Draw the marks
                // 2 RegPoly objects for hour marks and sec marks
                paint.setColor(Color.BLACK);
                paint.setTextSize(32);
                paint.setStrokeWidth(8);


                if(flag) {
                    paint.setColor(clockColor);
                }

                RegPoly secMarks = new RegPoly(60,getWidth()/2, getHeight()/2, length, canvas, paint);
                RegPoly hourMarks = new RegPoly(12,getWidth()/2, getHeight()/2, length-30, canvas, paint);

                RegPoly hourHand = new RegPoly(60,getWidth()/2, getHeight()/2, length-150, canvas, paint);
                RegPoly minHand = new RegPoly(60,getWidth()/2, getHeight()/2, length-80, canvas, paint);
                RegPoly secHand = new RegPoly(60,getWidth()/2, getHeight()/2, length-40, canvas, paint);
                RegPoly mSecHand = new RegPoly(60,getWidth()/2, getHeight()/2, length-360, canvas, paint);

                RegPoly numbers = new RegPoly(12,getWidth()/2, getHeight()/2, length+50, canvas, paint);

                numbers.drawNumbers();

                secMarks.drawNodes();
                hourMarks.drawNodes();

                Calendar calendar = Calendar.getInstance();

                sec = calendar.get(Calendar.SECOND);
                hour = calendar.get(Calendar.HOUR);
                minute = calendar.get(Calendar.MINUTE);
                mSec = calendar.get(Calendar.MILLISECOND);
//                Log.d("mSec", String.valueOf(mSec));

                secHand.drawRadius(sec + 45);
                minHand.drawRadius(minute + 45);
                hourHand.drawRadius((hour * 5) + (minute / 12) + 45);
                mSecHand.drawRadius(mSec);

                // Sleep for 1 second
                try {
//                    Thread.sleep(100);
//                    sec = sec + 1;
                } catch (Exception e) {

                }

                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}