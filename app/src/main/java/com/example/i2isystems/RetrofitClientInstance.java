package com.example.i2isystems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://35.224.165.76:8080/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
    public static IRegisterApi getPackageId(){

        IRegisterApi iRegisterApi = getRetrofit().create(IRegisterApi.class);
        return iRegisterApi;
    }
    public static IPackageApi getPackageIdName(){

        IPackageApi iPackageApi = getRetrofit().create(IPackageApi.class);

        return iPackageApi;
    }
    public static ILoginApi getUserService(){

        ILoginApi iLoginApi = getRetrofit().create(ILoginApi.class);

        return iLoginApi;
    }
    public static IPackageInfo getPackageInfo(){

        IPackageInfo iPackageInfo = getRetrofit().create(IPackageInfo.class);

        return  iPackageInfo;
    }
    public static IPackageBalanceRemaining getBalanceRemaining(){
        IPackageBalanceRemaining iPackageBalanceRemaining = getRetrofit().create(IPackageBalanceRemaining.class);

        return iPackageBalanceRemaining;
    }

    public static ISubscriberApi getSubscriber(){
        ISubscriberApi iSubscriberApi = getRetrofit().create(ISubscriberApi.class);
        return  iSubscriberApi;
    }
}
