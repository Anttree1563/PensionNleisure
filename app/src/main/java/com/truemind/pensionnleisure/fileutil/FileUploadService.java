package com.truemind.pensionnleisure.fileutil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.truemind.pensionnleisure.Constants;
import com.truemind.pensionnleisure.network.FileUpload;
import com.truemind.pensionnleisure.network.HttpFileUpload;

import java.io.File;

/**
 * Created by 현석 on 2017-04-01.
 */
public class FileUploadService extends Service{

    private String url = Constants.URL;
    private String filePath = Constants.FILE_PATH;

    private File txtFile = new File(filePath);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyTag", "-------Service OnCreate");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        Log.d("MyTag", "-------Service onStart");
        if(txtFile.exists()){/*
            FileUpload fileUpload = new FileUpload();
            fileUpload.uploadFile(txtFile);*/
            new HttpFileUpload(url, filePath);

        }else{
            Log.d("MyTag", "파일 없음");
        }
        super.onStart(intent, startId);
    }
}
