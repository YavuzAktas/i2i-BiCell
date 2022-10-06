package com.example.i2isystems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPackageInfo {

    @GET("package/packageInfoInObject")
    Call<PackageInfoRequest> getPackageInfo(@Query("MSISDN") String msisdn);
}
