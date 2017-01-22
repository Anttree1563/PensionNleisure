package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by 현석 on 2017-01-20.
 */
public class MapActivity extends Activity {

    ImageButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
        initListener();
    }

    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.map_btn_back);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
