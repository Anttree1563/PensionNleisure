package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.truemind.pensionnleisure.softkeyboard.SoftKeyboard;

public class MainActivity extends Activity {
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    ImageButton btn0, btn1, btn2, btn3, btn4, btn5;
    String name, phone;
    String namepref, phonepref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(namepref!=""||phonepref!="") {
            getPreferences();
            name = namepref;
            phone = phonepref;
        }else{
            Intent intent = getIntent();
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            savePreferences();
        }

        //Toast.makeText(MainActivity.this, namepref, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, phonepref, Toast.LENGTH_SHORT).show();
        initView();
        initListener();
    }

    private void getPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        pref.getString(namepref, name);
        pref.getString(phonepref, phone);
    }

    private void savePreferences() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(namepref, name);
        editor.putString(phonepref, phone);
        editor.commit();
    }
    private void removeAllPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    private void initListener() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, KeyboardSetActivity.class);
                startActivity(intent1);
                finish();
            }
        });
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
                intent1.putExtra("name", namepref);
                intent1.putExtra("phone", phonepref);
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
        btn0 = (ImageButton) findViewById(R.id.main_btn_keyboard);
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
            removeAllPreferences();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "\"Back\"버튼 을 한번 더 눌러 종료",Toast.LENGTH_SHORT).show();
        }
    }
}