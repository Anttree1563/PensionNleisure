package com.truemind.pensionnleisure.network;

import com.truemind.pensionnleisure.Constants;
import com.truemind.pensionnleisure.fileutil.FileData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 현석 on 2017-02-17.
 */
public interface NetworkService {
    String fileUploadURL = Constants.fileUploadURL;
    @Multipart
    @POST(fileUploadURL)
    Call<Void> fileToServer(@Part MultipartBody.Part txtFile, @Part("id")RequestBody body);
   // Call<ResponseBody> fileToServer(@Part MultipartBody.Part fileData);
}
