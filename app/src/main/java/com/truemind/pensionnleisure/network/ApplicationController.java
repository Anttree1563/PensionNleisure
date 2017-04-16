package com.truemind.pensionnleisure.network;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truemind.pensionnleisure.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 현석 on 2017-02-17.
 */
public class ApplicationController extends Application {

    private static ApplicationController instance;
    public static ApplicationController getInstance() {return instance;}
    String baseUrl= Constants.URL;;

    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationController. instance = this;
        buildNetworkService(); }
    private NetworkService networkService;
    public NetworkService getNetworkService() {return networkService;}
    public void buildNetworkService(){
        synchronized (ApplicationController. class){
            if( networkService == null){
                Gson gson = new GsonBuilder()
                        .setDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();
                GsonConverterFactory factory =
                        GsonConverterFactory. create(gson);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl( baseUrl)
                        .addConverterFactory(factory)
                        .build();
                networkService = retrofit.create(NetworkService. class);
            }
        }
    }
}
