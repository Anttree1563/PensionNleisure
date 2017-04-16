package com.truemind.pensionnleisure.ui;

/**
 * Created by 현석 on 2017-01-19.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.truemind.pensionnleisure.Constants;
import com.truemind.pensionnleisure.R;
import com.truemind.pensionnleisure.fileutil.FileUploadService;
import com.truemind.pensionnleisure.softkeyboard.SoftKeyboard;

public class SplashActivity extends Activity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNum = tm.getLine1Number();
        new Constants().writePhone(phoneNum);
        startService(new Intent(this, SoftKeyboard.class));
        startService(new Intent(this, FileUploadService.class));

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
