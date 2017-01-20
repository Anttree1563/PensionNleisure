package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 현석 on 2017-01-20.
 */
public class GalleryActivity extends Activity {

    ImageButton btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initView();
        initListener();
    }

    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(GalleryActivity.this, LeisureActivity.class);
                startActivity(intent1);
                finish();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(GalleryActivity.this, PensionActivity.class);
                startActivity(intent1);
                finish();
            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }

        });

    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.gallery_btn1);
        btn2 = (ImageButton) findViewById(R.id.gallery_btn2);
        btn3 = (ImageButton) findViewById(R.id.gallery_btn_back);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
