package com.truemind.pensionnleisure.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.truemind.pensionnleisure.R;

/**
 * Created by 현석 on 2017-01-21.
 */
public class LeisureActivity  extends Activity {

    ImageView imageView;
    ImageButton btn1;
    LinearLayout linearLayout1, linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leisure);
        initView();
        initListener();
    }

    private void initListener() {

        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setVerticalScrollBarEnabled(true);
        imageView.setVerticalFadingEdgeEnabled(true);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(LeisureActivity.this, GalleryActivity.class);
                startActivity(intent1);
                finish();
            }

        });

    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.leisure_btn_back);
        imageView = (ImageView) findViewById(R.id.leisure_imageView);
        linearLayout1 = (LinearLayout) findViewById(R.id.leisure_btn_layout);
        linearLayout2 = (LinearLayout) findViewById(R.id.leisure_view_layout);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LeisureActivity.this, GalleryActivity.class);
        startActivity(intent);
        finish();
    }

}