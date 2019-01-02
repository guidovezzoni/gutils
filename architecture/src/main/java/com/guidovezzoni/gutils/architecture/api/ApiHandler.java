package com.guidovezzoni.gutils.architecture.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler<A> {
    private final Class<A> serviceApiClass;
    private final String baseUrl;

    public ApiHandler(Class<A> serviceApiClass, String baseUrl) {
        this.serviceApiClass = serviceApiClass;
        this.baseUrl = baseUrl;
    }

    public A getService() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceApiClass);
    }
}
