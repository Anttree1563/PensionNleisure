package com.truemind.pensionnleisure.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.truemind.pensionnleisure.R;

/**
 * Created by 현석 on 2017-01-20.
 */
public class ReservActivity extends Activity {
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6;
    Drawable btn1_checked, btn2_checked, btn3_checked, btn4_checked, btn5_checked;
    Drawable btn1_unchecked, btn2_unchecked, btn3_unchecked, btn4_unchecked, btn5_unchecked;
    int button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        button = 0;

        btn1_checked = getResources().getDrawable(R.mipmap.reserv_btn1_checked);
        btn2_checked = getResources().getDrawable(R.mipmap.reserv_btn2_checked);
        btn3_checked = getResources().getDrawable(R.mipmap.reserv_btn3_checked);
        btn4_checked = getResources().getDrawable(R.mipmap.reserv_btn4_checked);
        btn5_checked = getResources().getDrawable(R.mipmap.reserv_btn5_checked);

        btn1_unchecked = getResources().getDrawable(R.mipmap.reserv_btn1);
        btn2_unchecked = getResources().getDrawable(R.mipmap.reserv_btn2);
        btn3_unchecked = getResources().getDrawable(R.mipmap.reserv_btn3);
        btn4_unchecked = getResources().getDrawable(R.mipmap.reserv_btn4);
        btn5_unchecked = getResources().getDrawable(R.mipmap.reserv_btn5);

        initView();
        initListener();

    }
    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                btn1.setBackground(btn1_checked);
                btn2.setBackground(btn2_unchecked);
                btn3.setBackground(btn3_unchecked);
                btn4.setBackground(btn4_unchecked);
                btn5.setBackground(btn5_unchecked);
                button = 1;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                btn1.setBackground(btn1_unchecked);
                btn2.setBackground(btn2_checked);
                btn3.setBackground(btn3_unchecked);
                btn4.setBackground(btn4_unchecked);
                btn5.setBackground(btn5_unchecked);
                button = 2;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                btn1.setBackground(btn1_unchecked);
                btn2.setBackground(btn2_unchecked);
                btn3.setBackground(btn3_checked);
                btn4.setBackground(btn4_unchecked);
                btn5.setBackground(btn5_unchecked);
                button = 3;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                btn1.setBackground(btn1_unchecked);
                btn2.setBackground(btn2_unchecked);
                btn3.setBackground(btn3_unchecked);
                btn4.setBackground(btn4_checked);
                btn5.setBackground(btn5_unchecked);
                button = 4;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                btn1.setBackground(btn1_unchecked);
                btn2.setBackground(btn2_unchecked);
                btn3.setBackground(btn3_unchecked);
                btn4.setBackground(btn4_unchecked);
                btn5.setBackground(btn5_checked);
                button = 5;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if(button != 0) {
                    Intent intent1 = new Intent(ReservActivity.this, SpecActivity.class);
                    intent1.putExtra("button", button);
                    startActivity(intent1);
                    finish();
                }else{
                    Toast.makeText(ReservActivity.this, "방을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.reserv_btn1);
        btn2 = (ImageButton) findViewById(R.id.reserv_btn2);
        btn3 = (ImageButton) findViewById(R.id.reserv_btn3);
        btn4 = (ImageButton) findViewById(R.id.reserv_btn4);
        btn5 = (ImageButton) findViewById(R.id.reserv_btn5);
        btn6 = (ImageButton) findViewById(R.id.reserv_btn6);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReservActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}