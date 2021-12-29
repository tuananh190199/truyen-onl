package com.example.truyenol.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {
    static final String BASE_URL = "https://corona.lmao.ninja/v2/";

    @GET("countries")
    Call<List<CountryData>> getCountryData();
}
