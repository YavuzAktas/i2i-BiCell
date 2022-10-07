package com.example.i2isystems;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISubscriberApi {

    @GET("/subscribe/get-sub-by-msisdn")
    Call<SubscriberRequest> subscriber(@Query("MSISDN") String msisdn);
}
