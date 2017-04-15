package com.truemind.pensionnleisure.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.truemind.pensionnleisure.R;

/**
 * Created by 현석 on 2017-01-21.
 */
public class PensionActivity extends Activity {

    ImageView imageView;
    ImageButton btn1, btn2;
    LinearLayout linearLayout1, linearLayout2;
    Drawable previous, next, pension_pic, pension_pic2;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pension);
        previous = getResources().getDrawable(R.mipmap.pension_btn_previous);
        next = getResources().getDrawable(R.mipmap.pension_btn_next);
        pension_pic2 = getResources().getDrawable(R.mipmap.pension_pic2);
        pension_pic = getResources().getDrawable(R.mipmap.pension_pic);
        initView();
        initListener();
    }

    private void initListener() {

        imageView.setVerticalScrollBarEnabled(true);
        imageView.setVerticalFadingEdgeEnabled(true);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(PensionActivity.this, GalleryActivity.class);
                startActivity(intent1);
                finish();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                    if(count==0) {
                        btn2.setBackground(previous);
                        imageView.setBackground(pension_pic2);
                        count--;
                    }
                    else{
                        btn2.setBackground(next);
                        imageView.setBackground(pension_pic);
                        count++;
                    }
                }

        });

    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.pension_btn_back);
        btn2 = (ImageButton) findViewById(R.id.pension_btn);
        imageView = (ImageView) findViewById(R.id.pension_imageView);
        linearLayout1 = (LinearLayout) findViewById(R.id.pension_btn_layout);
        linearLayout2 = (LinearLayout) findViewById(R.id.pension_view_layout);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PensionActivity.this, GalleryActivity.class);
        startActivity(intent);
        finish();
    }

}