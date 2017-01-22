package com.truemind.pensionnleisure;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by 현석 on 2017-01-22.
 */
public class SpecActivity extends Activity {
    ImageView view1, view2;
    TextView text1, text2, text3;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6;
    Drawable room1_1, room1_2, room1_3, room1_4, room1_5;
    Drawable room3_1;
    Drawable room4_1, room4_2, room4_3;
    Drawable room5_1, room5_2, room5_3;
    Drawable manu1, manu2, manu3, manu4, manu5;

    String strDate, strDate2;
    int button, year, month, day;
    int checkin, checkout;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec);
        button = getIntent().getIntExtra("button", 1);

        room1_1 = getResources().getDrawable(R.mipmap.spec_room1_1);
        room1_2 = getResources().getDrawable(R.mipmap.spec_room1_2);
        room1_3 = getResources().getDrawable(R.mipmap.spec_room1_3);
        room1_4 = getResources().getDrawable(R.mipmap.spec_room1_4);
        room1_5 = getResources().getDrawable(R.mipmap.spec_room1_5);

        room3_1 = getResources().getDrawable(R.mipmap.spec_room3_1);

        room4_1 = getResources().getDrawable(R.mipmap.spec_room4_1);
        room4_2 = getResources().getDrawable(R.mipmap.spec_room4_2);
        room4_3 = getResources().getDrawable(R.mipmap.spec_room4_3);

        room5_1 = getResources().getDrawable(R.mipmap.spec_room5_1);
        room5_2 = getResources().getDrawable(R.mipmap.spec_room5_2);
        room5_3 = getResources().getDrawable(R.mipmap.spec_room5_3);

        manu1 = getResources().getDrawable(R.mipmap.spec_manual1);
        manu2 = getResources().getDrawable(R.mipmap.spec_manual2);
        manu3 = getResources().getDrawable(R.mipmap.spec_manual3);
        manu4 = getResources().getDrawable(R.mipmap.spec_manual4);
        manu5 = getResources().getDrawable(R.mipmap.spec_manual5);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day= calendar.get(Calendar.DAY_OF_MONTH);


        initView();
        switch (button){
            case 1:
                text1.setText("[ Deluxe twin ]");
                view1.setBackground(room1_1);
                view2.setBackground(manu1);
                btn1.setBackground(room1_1);
                btn2.setBackground(room1_2);
                btn3.setBackground(room1_3);
                btn4.setBackground(room1_4);
                btn5.setBackground(room1_5);
                break;

            case 2:
                text1.setText("[ Deluxe double ]");
                view1.setBackground(room1_1);
                view2.setBackground(manu2);
                btn1.setBackground(room1_1);
                btn2.setBackground(room1_2);
                btn3.setBackground(room1_3);
                btn4.setBackground(room1_4);
                btn5.setBackground(room1_5);
                break;

            case 3:
                text1.setText("[ Family ]");
                view1.setBackground(room3_1);
                view2.setBackground(manu3);
                btn1.setBackground(room3_1);
                btn2.setBackground(room1_5);
                btn3.setClickable(false);
                btn4.setClickable(false);
                btn5.setClickable(false);
                btn3.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                break;

            case 4:
                text1.setText("[ Junior suite ]");
                view1.setBackground(room4_1);
                view2.setBackground(manu4);
                btn1.setBackground(room4_1);
                btn2.setBackground(room4_2);
                btn3.setBackground(room4_3);
                btn4.setClickable(false);
                btn5.setClickable(false);
                btn4.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                break;

            case 5:
                text1.setText("[ Superior suite ]");
                view1.setBackground(room5_1);
                view2.setBackground(manu5);
                btn1.setBackground(room5_1);
                btn2.setBackground(room5_2);
                btn3.setBackground(room5_3);
                btn4.setClickable(false);
                btn5.setClickable(false);
                btn4.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                break;

        }
        initListener();

    }
    private void initListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                if(button == 1 || button == 2)
                    view1.setBackground(room1_1);
                else if(button == 3)
                    view1.setBackground(room3_1);
                else if(button == 4)
                    view1.setBackground(room4_1);
                else if(button == 5)
                    view1.setBackground(room5_1);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                if(button == 1 || button == 2)
                    view1.setBackground(room1_2);
                else if(button == 3)
                    view1.setBackground(room1_5);
                else if(button == 4)
                    view1.setBackground(room4_2);
                else if(button == 5)
                    view1.setBackground(room5_2);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                if(button == 1 || button == 2)
                    view1.setBackground(room1_3);
                else if(button == 3)
                    view1.setBackground(room1_3);
                else if(button == 4)
                    view1.setBackground(room4_3);
                else if(button == 5)
                    view1.setBackground(room5_3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                if(button == 1 || button == 2)
                    view1.setBackground(room1_4);
                else if(button == 3)
                    view1.setBackground(room1_4);
                else if(button == 4)
                    view1.setBackground(room1_4);
                else if(button == 5)
                    view1.setBackground(room1_4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override

            public void onClick(View v) {
                if(button == 1 || button == 2)
                    view1.setBackground(room1_5);
                else if(button == 3)
                    view1.setBackground(room1_5);
                else if(button == 4)
                    view1.setBackground(room1_5);
                else if(button == 5)
                    view1.setBackground(room1_5);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SpecActivity.this, dateSetListener2, year, month, day).show();
                text2.setText(strDate);

            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SpecActivity.this, dateSetListener3, year, month, day).show();
                text3.setText(strDate2);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkin < checkout) {
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(SpecActivity.this);
                    builder.setTitle("Sorry, we're fully booked!")        // 제목 설정
                            .setMessage("죄송합니다, 입력하신 날짜에 가능한 방이 없습니다.")// 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                // 확인 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton){
                                    Intent intent1 = new Intent(SpecActivity.this, SpecActivity.class);
                                    intent1.putExtra("button", button);
                                    startActivity(intent1);
                                    finish();
                                }
                            });
                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기*/
                    CustomDialog dialog;
                    dialog = new CustomDialog(SpecActivity.this, "Sorry, we're fully booked!", "죄송합니다, 입력하신 날짜에 가능한 방이 없습니다.");
                    dialog.show();
                }else{
                    Toast.makeText(SpecActivity.this, "체크아웃 날짜는 체크인 날짜보다 뒤에 있어야 합니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener2
        = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            checkin = year*10000 + (monthOfYear+1)*100 + dayOfMonth;
            strDate  = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
            Toast.makeText(SpecActivity.this, strDate, Toast.LENGTH_SHORT).show();

            text2.setText(strDate);
        }
    };

    private DatePickerDialog.OnDateSetListener dateSetListener3
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            checkout = year*10000 + (monthOfYear+1)*100 + dayOfMonth;
            strDate2 = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
            Toast.makeText(SpecActivity.this, strDate2, Toast.LENGTH_SHORT).show();

            text3.setText(strDate2);
        }
    };


    private void initView() {
        btn1 = (ImageButton) findViewById(R.id.imageButton1);
        btn2 = (ImageButton) findViewById(R.id.imageButton2);
        btn3 = (ImageButton) findViewById(R.id.imageButton3);
        btn4 = (ImageButton) findViewById(R.id.imageButton4);
        btn5 = (ImageButton) findViewById(R.id.imageButton5);
        btn6 = (ImageButton) findViewById(R.id.spec_btn6);
        view1 = (ImageView) findViewById(R.id.imageView);
        view2 = (ImageView) findViewById(R.id.imageView2);
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SpecActivity.this, ReservActivity.class);
        startActivity(intent);
        finish();
    }

}