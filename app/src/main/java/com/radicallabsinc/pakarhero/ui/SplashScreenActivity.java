package com.radicallabsinc.pakarhero.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getStartIntent(getBaseContext()));
                finish();
            }
        },3000);
    }
}
