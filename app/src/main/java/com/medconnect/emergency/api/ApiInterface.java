package com.medconnect.emergency.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/user/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/emergency/trigger")
    Call<Map<String, Object>> triggerEmergency(@Body Map<String, Object> data);
}
