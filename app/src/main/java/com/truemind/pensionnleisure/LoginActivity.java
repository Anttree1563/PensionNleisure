package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by 현석 on 2017-01-23.
 */
public class LoginActivity extends Activity {
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    ImageButton btn1;
    EditText nameEdit, phoneEdit;
    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNum = tm.getLine1Number();
        name = "";
        phone = "";
        phone = phoneNum;

        initView();

        phoneEdit.setText(phone);

        initListener();

    }
    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                phone = phoneEdit.getText().toString();
                name = nameEdit.getText().toString();
                if(phone==""||name==""){
                    Toast.makeText(LoginActivity.this, "이름과 전화번호를 확인해 주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "환영합니다 "+name+"님", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.login_btn);
        nameEdit = (EditText) findViewById(R.id.editText1);
        phoneEdit = (EditText) findViewById(R.id.editText2);
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
