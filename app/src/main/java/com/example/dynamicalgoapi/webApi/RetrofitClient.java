package com.example.dynamicalgoapi.webApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.dynamicalgoapi24.somee.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
