package com.truemind.pensionnleisure.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.truemind.pensionnleisure.R;

/**
 * Created by 현석 on 2017-01-30.
 */
public class KeyboardSetActivity extends Activity {

    ImageView imageView;
    ImageButton btn1, btn2, btn3;
    LinearLayout linearLayout1, linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboardset);
        initView();
        initListener();
    }

    private void initListener() {

        //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //imageView.setVerticalScrollBarEnabled(true);
        //imageView.setVerticalFadingEdgeEnabled(true);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent1 = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent1 = new Intent(Settings.ACTION_SETTINGS);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }

        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(KeyboardSetActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }

        });

    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.keyboardset_btn1);
        btn2 = (ImageButton) findViewById(R.id.keyboardset_btn2);
        btn3 = (ImageButton) findViewById(R.id.keyboardset_btn3);

        //imageView = (ImageView) findViewById(R.id.keyboardset_manual);
        //linearLayout1 = (LinearLayout) findViewById(R.id.keyboardset_manual_layout);
        linearLayout2 = (LinearLayout) findViewById(R.id.keyboardset_btn_layout);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KeyboardSetActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
