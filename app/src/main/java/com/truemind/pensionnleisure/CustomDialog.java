package com.truemind.pensionnleisure;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 현석 on 2017-01-23.
 */


public class CustomDialog extends android.app.Dialog {
    TextView mTitleView;
    TextView mTextView;
    Button mRightButton;
    String mTitle, mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.custom_dialog_prompt);

        mTitleView = (TextView) findViewById(R.id.dialog_prompt_titleview);
        mTextView = (TextView) findViewById(R.id.dialog_prompt_textview);
        mRightButton = (Button) findViewById(R.id.dialog_prompt_ok);

        mTitleView.setText(mTitle);
        mTextView.setText(mContent);

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CustomDialog(Context context, String title, String content){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mContent = content;
    }
}