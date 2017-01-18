package com.truemind.pensionnleisure;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 현석 on 2017-01-18.
 */
public class Keylogger extends Service {
    int screenWidth, screenHeight;
    private View tv;
    //private TextView mPopupView;//항상 보이게 할 뷰
    private WindowManager.LayoutParams mParams;//layout params 객체. 뷰의 위치 및 크기
    private WindowManager mWindowManager;//윈도우 매니저

    @Override
    public void onCreate() {
        super.onCreate();
        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        //-------------------------------------------------------------------------------------------
        View tv = new View(this);
/*

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,   //항상 최 상위에 있게
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,       //터치 인식
                PixelFormat.TRANSLUCENT);//투명

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE); //윈도 매니저
        wm.addView(tv, params);  //최상위 윈도우에 뷰 넣기. permission필요.
*/
/*

        mPopupView = new TextView(this);//뷰 생성
        mPopupView.setText("이 뷰는 항상 위에 있다.");//텍스트 설정
        mPopupView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);//텍스트 크기 18sp
        mPopupView.setTextColor(Color.BLUE);//글자 색상
        mPopupView.setBackgroundColor(Color.argb(127, 0, 255, 255));//텍스트뷰 배경 색
*/
        final int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics());

        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());

        //최상위 윈도우에 넣기 위한 설정
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //width, height,
                //WindowManager.LayoutParams.TYPE_PHONE,//항상 최상위. 터치 이벤트 받을 수 있음.
                //WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,//포커스를 가지지 않음
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,   //항상 최 상위에 있게
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);//투명
        mParams.gravity = Gravity.LEFT | Gravity.TOP;//왼쪽 상단에 위치하게 함.

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);  //윈도우 매니저
        mWindowManager.addView(tv, mParams);      //윈도우에 뷰 넣기. permission 필요.

        tv.setOnTouchListener(mViewTouchListener);              //팝업뷰에 터치 리스너 등록

    }


    private View.OnTouchListener mViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            //super.onTouchEvent(event);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                float x = event.getX();
                float y = event.getY();

                String msg = "터치를 입력받음 : " + x + " / " + y;

                Toast.makeText(Keylogger.this, msg, Toast.LENGTH_SHORT).show();
                return true;

            }
            return false;
        }
    };



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
/*

        if(!mp3File.exists()){
            Toast.makeText(this, "파일이 없습니다", Toast.LENGTH_LONG).show();
            stopSelf();
        }else{
            new Thread(task).start();
        }
*/

        return super.onStartCommand(intent, flags, startId);
    }
/*

    Runnable task = new Runnable() {
        public void run() {
            try {

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
*/

    private Context getContext()//************************현재 context를 불러오는 함수
    {
        Context mContext;
        mContext = getApplicationContext();
        return mContext;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
/*
    @Override
    public void onDestroy() {
        if (mWindowManager != null) {
            if (mPopupView != null) mWindowManager.removeView(mPopupView);
            if (mSeekBar != null) mWindowManager.removeView(mSeekBar);
        }
        super.onDestroy();
    }
}
*/

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(tv != null)        //서비스 종료시 뷰 제거
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(tv);
            tv = null;
        }

    }

}

