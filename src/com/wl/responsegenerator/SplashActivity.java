package com.wl.responsegenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {
	
	private final String TAG = "Splash:";
	// Splash screen timer
    private static final int SPLASH_TIME_OUT = 3_000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Splash start");
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                Log.i(TAG, "Splash end");
                finish();
            } //run()
        }, SPLASH_TIME_OUT);
    } //onCreate(Bundle savedInstanceState)
} //class
