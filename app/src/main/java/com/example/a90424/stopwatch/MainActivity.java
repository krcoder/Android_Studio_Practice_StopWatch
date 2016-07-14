package com.example.a90424.stopwatch;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    TextView textView2;
    TextView textView3, textView4, textView5, textView6, textView7;
    Button button, button2, button3, button_start;
    Message msg;
    long start,t;
    int count =0;
    int state =0;
    String strNow;
    String go;
    int tra=0;

    Boolean isRunning = true;
    ThreadClass tc = new ThreadClass();
    HandlerClass hc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        hc = new HandlerClass();

        tc.start();


    }

    @Override
    public void onClick(View view) {
        if(view == button){
            if (tra == 0) {
                start = System.currentTimeMillis();
            }

            if(state==1){
                state=0;
            }
            else if(state==0){
                state=1;
            }
            go = strNow;
            tra=1;
        }
        else if(view == button2){
            switch(count){
                case 0:
                    textView2.setText(strNow);
                    break;
                case 1:
                    textView3.setText(strNow);
                    break;
                case 2:
                    textView4.setText(strNow);
                    break;
                case 3:
                    textView5.setText(strNow);
                    break;
                case 4:
                    textView6.setText(strNow);
                    break;
            }
            count++;
            if(count>4){
                count=0;
            }
            go="00:00:00";

        }
        else if(view == button3){
            textView7.setText("00:00:00");
            textView2.setText("00:00:00");
            textView3.setText("00:00:00");
            textView4.setText("00:00:00");
            textView5.setText("00:00:00");
            textView6.setText("00:00:00");
            go="00:00:00";
            tra=0;
            count=0;
        }

    }

    class HandlerClass extends Handler{
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == 0){
                if(state == 1){
                    long t = System.currentTimeMillis()-start;
                    Date date = new Date(t);
                    SimpleDateFormat sdfNow = new SimpleDateFormat("mm:ss:SS");
                    strNow = sdfNow.format(date);
                    textView7.setText(strNow); //strNow흘러가는중
                }
                if(state == 0){
                    textView7.setText(go);
                }

            }


        }
    }

    class ThreadClass extends Thread{
        public void run(){
            while(isRunning){
                SystemClock.sleep(10);
                long t = System.currentTimeMillis();

                //Log.d("msg","현재시간 : " + t);
                //textView.setText("");

                hc.sendEmptyMessage(0);
                /*
                Message msg = new Message();
                msg.what = 1; //TIME NOTIFICATION
                msg.obj = t;

                hc.sendMessage(msg);
                */
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        isRunning = false;
    }































}