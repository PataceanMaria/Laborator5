package com.example.threads_tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private volatile boolean stopThread=false;
    private final Handler mainHandler=new Handler();

    private EditText interval;
    private TextView rezultat;
    private TextView answer;
    private EditText presupunere;
    String val;


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        interval=findViewById(R.id.Interval);
        rezultat=findViewById(R.id.Rezultat);
        presupunere=findViewById(R.id.Nr_ales);
        answer=findViewById(R.id.Thread_answer);

    }
    public void startThread(View v)
    {
        stopThread=false;
        Random random=new Random();
        String number= interval.getText().toString();
        val= String.valueOf(random.nextInt(Integer.parseInt(number)));
        Newthread runnable=new Newthread(10);
        new Thread(runnable).start();

    }
    public void Verify(View v)
    {
        stopThread=true;
        Newthread thread=new Newthread(10);


                if(presupunere.getText().toString().equals(val))
                    rezultat.setText("Ai ghicit  " );
                else
                    rezultat.setText("N ai ghicit  "+val);


    }
    class Newthread implements Runnable
    {
        int seconds;
        Newthread(int seconds)
        {
            this.seconds=seconds;
        }
        @Override
        public void run(){
            Random random=new Random();
            String number= interval.getText().toString();
            String secondval= String.valueOf(random.nextInt(Integer.parseInt(number)));
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    answer.setText("Second thread answer : " +secondval);

                }
            });

        }

    }





}