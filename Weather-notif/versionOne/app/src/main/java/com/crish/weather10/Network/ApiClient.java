package com.crish.weather10.Network;

import com.crish.weather10.Response.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("/data/2.5/weather")
    Call<ResponseWeather> getData(@Query("lat") double lat,@Query("lon") double lon,@Query("appid") String apiId);
}
