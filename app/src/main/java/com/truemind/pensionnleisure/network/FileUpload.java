package com.truemind.pensionnleisure.network;

import android.util.Log;

import com.truemind.pensionnleisure.Constants;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 현석 on 2017-04-03.
 */
public class FileUpload {

    public String phoneNum = Constants.phone;
    private NetworkService networkService;

    private void initNetworkService() {
        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    public void uploadFile(File txtFile, String id) {
        initNetworkService();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), txtFile);
        RequestBody dummyRequestBody = RequestBody.create(MediaType.parse("text/plain"), phoneNum+id);
        MultipartBody.Part txtFileBody = MultipartBody.Part.createFormData("txtFile", txtFile.getName(), requestBody);

        Call<Void> addTxtCall = networkService.fileToServer(txtFileBody, dummyRequestBody);
        addTxtCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){
                    Log.d("MyTag", "성공");
                }else{
                    Log.d("MyTag", "망함");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("MyTag", "개망");
            }
        });

    }

}
