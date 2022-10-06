package com.example.i2isystems;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ILoginApi {
        @Headers({"Content-Type: application/json"})
        @POST("login/")
        Call<LoginRequest> login(@Body LoginRequest L);
}


