package com.example.i2isystems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISubscriberApi {

    @GET("/subscribe/getSubs/")
    Call<SubscriberRequest> subscriber();
}
