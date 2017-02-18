package com.truemind.pensionnleisure;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 현석 on 2017-02-17.
 */
public interface NetworkService {
    @POST( "/pictures")
    Call<ResponseBody> fileToServer(@Part MultipartBody.Part fileData);
}
