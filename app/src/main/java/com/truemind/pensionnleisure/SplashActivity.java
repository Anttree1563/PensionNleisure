package com.truemind.pensionnleisure;

/**
 * Created by 현석 on 2017-01-19.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.truemind.pensionnleisure.softkeyboard.SoftKeyboard;

public class SplashActivity extends Activity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startService(new Intent(this, SoftKeyboard.class));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);

    }
}
