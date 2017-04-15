package com.truemind.pensionnleisure.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.truemind.pensionnleisure.R;

/**
 * Created by 현석 on 2017-01-20.
 */
public class IntroActivity extends Activity {

    ImageButton btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initView();
        initListener();
    }

    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                /*
                Intent intent1 = new Intent(IntroActivity.this, .class);
                startActivity(intent1);
                finish();*/

                Toast.makeText(getApplicationContext(), "서비스 준비 중 입니다.",Toast.LENGTH_SHORT).show();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }

        });


    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.intro_btn1);
        btn2 = (ImageButton) findViewById(R.id.intro_btn2);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
