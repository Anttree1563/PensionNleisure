package com.truemind.pensionnleisure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String keyDown2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(KeyEvent.ACTION_DOWN == event.getAction()){
            char keyDown = (char)event.getUnicodeChar(); //키보드에서 입력받은 값
            keyDown2 += keyDown;
            Log.d("MyTag", "keyDown"+keyDown2);
        }
        return false;
    }




}
