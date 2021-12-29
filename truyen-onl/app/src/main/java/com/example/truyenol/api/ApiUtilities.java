package com.example.truyenol.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static Retrofit retrofit = null;
    public static ApiInterFace getApiInterface(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterFace.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(ApiInterFace.class);
    }

}
