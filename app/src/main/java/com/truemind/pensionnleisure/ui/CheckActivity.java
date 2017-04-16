package com.truemind.pensionnleisure.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.truemind.pensionnleisure.Constants;
import com.truemind.pensionnleisure.R;
import com.truemind.pensionnleisure.util.CustomDialog;

/**
 * Created by 현석 on 2017-01-20.
 */
public class CheckActivity extends Activity {
    ImageButton btn1, btn2;
    String name, phone;
    TextView nameEdit, phoneEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phone = Constants.phone;

        initView();
        nameEdit.setText(name);
        phoneEdit.setText(phone);
        //Toast.makeText(CheckActivity.this, name, Toast.LENGTH_SHORT).show();
        //Toast.makeText(CheckActivity.this, phone, Toast.LENGTH_SHORT).show();
        initListener();
    }

    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent1 = new Intent(CheckActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                phone = phoneEdit.getText().toString();
                name = nameEdit.getText().toString();
                if(name==""||phone=="") {
                    Toast.makeText(getApplicationContext(), name + "이름과 핸드폰 번호가 입력되었는지 확인해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    CustomDialog dialog;
                    dialog = new CustomDialog(CheckActivity.this, "History not found", name+"님의 예약 내역이 존재하지 않습니다.");
                    dialog.show();
                }
            }
        });
    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.check_btn_back);
        btn2 = (ImageButton) findViewById(R.id.check_btn_confirm);
        nameEdit = (TextView) findViewById(R.id.editText1);
        phoneEdit = (TextView) findViewById(R.id.editText2);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CheckActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}