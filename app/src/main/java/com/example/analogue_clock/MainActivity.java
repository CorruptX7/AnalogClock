package com.example.analogue_clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ClockSurfaceView clock;
    TimerSurfaceView timer;

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, clockBtn, timerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the size of the screen (find out yourself)
        // int width = getWidth();
        clock = new ClockSurfaceView(this, 400);
        timer = new TimerSurfaceView(this, 400, 60);
        setContentView(R.layout.activity_main);
//        setContentView(clock);

        LinearLayout linearLayout = findViewById(R.id.surfaceView);
        linearLayout.addView(clock);

        clockBtn = findViewById(R.id.clock);
        timerBtn = findViewById(R.id.timer);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(244, 67, 54));
                timer.changeColor(Color.rgb(244, 67, 54));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(76, 175, 80));
                timer.changeColor(Color.rgb(76, 175, 80));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(33, 150, 243));
                timer.changeColor(Color.rgb(33, 150, 243));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(255, 235, 59));
                timer.changeColor(Color.rgb(255, 235, 59));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(255, 87, 34));
                timer.changeColor(Color.rgb(255, 87, 34));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(156, 39, 176));
                timer.changeColor(Color.rgb(156, 39, 176));
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(0, 188, 212));
                timer.changeColor(Color.rgb(0, 188, 212));
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.changeColor(Color.rgb(150, 0, 0));
                timer.changeColor(Color.rgb(150, 0, 0));
            }
        });

        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.onPauseTimerSurfaceView();
                linearLayout.removeView(timer);
                clock.onResumeClockSurfaceView();
                linearLayout.addView(clock);
            }
        });

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clock.onPauseClockSurfaceView();
                linearLayout.removeView(clock);
                timer.onResumeTimerSurfaceView();
                linearLayout.addView(timer);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                break;

            case R.id.item2:
                setContentView(R.layout.activity_main);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        clock.onPauseClockSurfaceView();
        timer.onPauseTimerSurfaceView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clock.onResumeClockSurfaceView();
        timer.onResumeTimerSurfaceView();
    }


}