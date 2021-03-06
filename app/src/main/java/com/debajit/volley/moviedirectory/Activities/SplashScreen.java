package com.debajit.volley.moviedirectory.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.debajit.volley.moviedirectory.R;

public class SplashScreen extends AppCompatActivity {

    private static int splashTimeOut=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }, splashTimeOut);

    }

}

