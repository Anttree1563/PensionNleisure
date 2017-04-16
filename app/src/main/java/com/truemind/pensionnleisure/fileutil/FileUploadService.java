package com.truemind.pensionnleisure.fileutil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.truemind.pensionnleisure.Constants;
import com.truemind.pensionnleisure.network.FileUpload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 현석 on 2017-04-01.
 */
public class FileUploadService extends Service{

    //private String url = Constants.URL;//useless
    private String filePath = Constants.FILE_PATH;
    private Thread fileUploadThread;
    private File txtFile = new File(filePath);
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyTag", "-------Service OnStartCommand");

        fileUploadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        if(txtFile.exists()){
                            SimpleDateFormat timeAndDateNow = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                            String id = timeAndDateNow.format(new Date(System.currentTimeMillis()));
                            FileUpload fileUpload = new FileUpload();
                            Log.d("MyTag", id+" 를 전송 시작");
                            fileUpload.uploadFile(txtFile, "_"+id);
                        }else{
                            Log.d("MyTag", "파일 없음");
                        }
                        Thread.sleep(3600000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        fileUploadThread.start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
