package com.example.i2isystems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPackageBalanceRemaining {

    @GET("/balance/balanceByMSISDN")
    Call<PackageBalanceRemaining> getPackageBalanceRemaining(@Query("MSISDN") String msisdn);
}
