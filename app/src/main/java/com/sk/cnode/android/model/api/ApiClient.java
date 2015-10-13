package com.sk.cnode.android.model.api;

import com.sk.cnode.android.utils.GsonWrapper;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public final class ApiClient {

    private static final String API_HOST = "https://cnodejs.org/api/";


    public static final ApiService ApiServiceInstance() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST).addConverterFactory(GsonConverterFactory.create(GsonWrapper.gson))
                .build();
        ApiService apiService = retrofit .create(ApiService.class);


        return apiService;
    }
}
