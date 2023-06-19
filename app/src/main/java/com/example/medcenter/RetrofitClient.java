package com.example.medcenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private  static final String BASE_URL = "http://127.0.0.1:8000/api/";
    private static Retrofit retrofit = null;
    public static InterfaceApi getRetrofitClient(){
        if ( retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();        }
        return retrofit.create(InterfaceApi.class);    }

}