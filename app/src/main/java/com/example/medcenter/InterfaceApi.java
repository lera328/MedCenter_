package com.example.medcenter;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {


   @GET("catalog/")
    Call<AnalisResult> getListAnalises();
   //@GET("catalog")
   // Call<List<Analis>> getListAnalises();

}
