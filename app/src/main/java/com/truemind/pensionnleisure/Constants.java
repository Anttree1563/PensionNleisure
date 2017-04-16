package com.truemind.pensionnleisure;

import android.os.Environment;

import java.io.File;

/**
 * Created by 현석 on 2017-04-02.
 */
public class Constants {

    public static final String URL = "http://54.145.130.55:3000";
    public static final String fileUploadURL = "m/addfile";
    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String FOLDER = "PNL";
    public static final String FILE_NAME = "pensionNLeisure.bak";

    public static final String FILE_PATH = SDCARD+ "/" + FOLDER + "/" +FILE_NAME;

    public static String phone ="";
    public void writePhone(String phone){
        this.phone = phone.replaceFirst("\\+82", "0");
    }

}
