package com.example.clement.robotremote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button avancer = (Button)findViewById(R.id.haut);
        Button reculer = (Button)findViewById(R.id.bas);
        Button droite = (Button)findViewById(R.id.droite);
        Button gauche = (Button)findViewById(R.id.gauche);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://192.168.42.1:9090/stream?width=320&height=200");


        //Starting the thread
        Thread controlMovement;
        controlMovement = new sendingMovement();
        controlMovement.start();

        //Getting usr command
        avancer.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent enfoncement) {
                if(enfoncement.getAction() == android.view.MotionEvent.ACTION_DOWN)
                {
                    robotState.changed = true;
                    robotState.ihmButtonDown = true;
                    robotState.command = "1111";
                    //System.out.println("1111");
                }
                else if(enfoncement.getAction() == android.view.MotionEvent.ACTION_UP)
                {
                    robotState.changed = true;
                    robotState.ihmButtonDown = false;
                    robotState.command = "0000";
                    //System.out.println("0000");
                }
                return true;
            }
        });

        reculer.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent enfoncement) {
                if(enfoncement.getAction() == android.view.MotionEvent.ACTION_DOWN)
                {
                    robotState.changed = true;
                    robotState.ihmButtonDown = true;
                    robotState.command = "2222";
                    System.out.println("2222");
                }
                else if(enfoncement.getAction() == android.view.MotionEvent.ACTION_UP)
                {
                    robotState.changed = true;
                    robotState.ihmButtonDown = false;
                    robotState.command = "0000";
                    System.out.println("0000");
                }
                return true;
            }
        });

        droite.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent enfoncement) {
                if(enfoncement.getAction() == android.view.MotionEvent.ACTION_DOWN)
                {
                    robotState.command = "2112";
                    System.out.println("2112");
                }
                else if(enfoncement.getAction() == android.view.MotionEvent.ACTION_UP)
                {
                    robotState.command = "0000";
                    System.out.println("0000");
                }
                return true;
            }
        });

        gauche.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent enfoncement) {
                if(enfoncement.getAction() == android.view.MotionEvent.ACTION_DOWN)
                {
                    robotState.command = "1221";
                    System.out.println("1221");
                }
                else if(enfoncement.getAction() == android.view.MotionEvent.ACTION_UP)
                {
                    robotState.command = "0000";
                    System.out.println("0000");
                }
                return true;
            }
        });

    }
}
