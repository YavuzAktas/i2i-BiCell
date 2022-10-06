package com.example.i2isystems;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRegisterApi {
    @Headers({"Content-Type: application/json"})
    @POST("subscribe/addSubscriber")
    Call<RegisterRequest> register(@Body RegisterRequest registerRequest);
}
