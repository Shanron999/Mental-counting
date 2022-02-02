package com.example.projetcalculemental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Play extends AppCompatActivity {
    private static long START_TIME_IN_MILIS= TimeUnit.SECONDS.toMillis(10); //1minute 6000
    private TextView Timer;
    private Button Timerbutton;
    private Button Valid;
    private CountDownTimer countDownTimer;
    private TextView False;
    private  long timeleftmiliesecond=START_TIME_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Timer = findViewById(R.id.Timer);
        Timerbutton = findViewById(R.id.Timerbutton);
        Valid=findViewById(R.id.valid);
        False=findViewById(R.id.False);

        Timerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startimer();
            }

        });
        Valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                reset();
                startimer();
            }
        });
        updateTimer();
    }

    private void startimer(){
        countDownTimer = new CountDownTimer(timeleftmiliesecond,1) {
            @Override
            public void onTick(long l) {
                timeleftmiliesecond=l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Timerbutton.setVisibility(View.VISIBLE);
                Valid.setVisibility(View.GONE);
                False.setVisibility(View.VISIBLE);
                False.setText(R.string.Time_Out);

                reset();


            }
        }.start();
        False.setVisibility(View.GONE);
        Timerbutton.setVisibility(View.GONE);
        Valid.setVisibility(View.VISIBLE);

    }


    private void updateTimer(){
        int minutes = (int)timeleftmiliesecond/60000;
        int second = (int)timeleftmiliesecond % 60000 / 1000;
        int miliesecond = (int)timeleftmiliesecond % 60000 %1000;
        int score = miliesecond+second*1000 + minutes*60000;
        String timelefttext;

        timelefttext = " "+minutes;
        timelefttext += ":";
        if (second < 10 ) timelefttext += "0";
        timelefttext += second;
        timelefttext +=":";
        if(miliesecond<100)timelefttext +="0";
        if(miliesecond<10)timelefttext +="0";
        timelefttext += miliesecond;

        /*String timelefttext = String.format(Locale.ENGLISH,"%02d : %02d"
                ,TimeUnit.MILLISECONDS.toMinutes(1)
                ,TimeUnit.MILLISECONDS.toSeconds(1)
                , TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));*/

        Timer.setText(timelefttext);


    }

    private  void reset ()
    {
        timeleftmiliesecond=START_TIME_IN_MILIS;
        updateTimer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.score_menu_button){
            Intent intent = new Intent(this,ScoreActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}