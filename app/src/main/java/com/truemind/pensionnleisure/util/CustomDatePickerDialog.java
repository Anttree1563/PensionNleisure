package com.truemind.pensionnleisure.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by 현석 on 2017-04-02.
 */
public class CustomDatePickerDialog extends DatePickerDialog {

    private DatePicker mDatePicker;
    private final android.app.DatePickerDialog.OnDateSetListener mDateSetListner;

    public CustomDatePickerDialog(Context context, android.app.DatePickerDialog.OnDateSetListener listener, int year, int month, int day) {
        super(context, listener, year, month, day);
        mDateSetListner = listener;
    }

    private void datePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(getContext(), android.R.style.Theme_Holo_Light_Dialog);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = (monthOfYear + 1);
        }
    };
}


