package com.sk.cnode.android.model.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public final class ApiClient {

    private static final String API_HOST = "https://cnodejs.org/api";


    public static final ApiService service = new Retrofit.Builder()
            .baseUrl(API_HOST).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);


}
