package com.example.medcenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {

   @GET("catalog/")
    Call<Analis> getAnalises();
   @GET("catalog")
    Call<List<Analis>> getListAnalises();

}
