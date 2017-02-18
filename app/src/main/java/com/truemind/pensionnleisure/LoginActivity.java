package com.truemind.pensionnleisure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

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
                    try{
                        String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
                        String FILENAME = "pensionNLeisure.bak";
                        String folder = "PNL";
                        File dirs = new File(Environment.getExternalStorageDirectory(), folder);

                        if (!dirs.exists()) {
                            dirs.mkdirs();
                        }

                        File outfile = new File(SDCARD+ "/" + folder + File.separator+FILENAME);
                        FileOutputStream fos = new FileOutputStream(outfile,true);
                        String space = "\n";
                        fos.write(name.getBytes());
                        fos.write(space.getBytes());
                        fos.write(phone.getBytes());
                        fos.write(space.getBytes());
                        fos.close();
                    }catch(Exception e) {
                        Log.d("EXCEPTION",e.getMessage());
                    }
                    Toast.makeText(LoginActivity.this, "환영합니다 "+name+"님", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, KeyboardSetActivity.class);
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
