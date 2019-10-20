package com.hk.demorestapimovie.movie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String baseUrl = "https://simplifiedcoding.net/demos/";
    private Retrofit retrofit;
    private static ApiClient apiClient;

    public ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public RetrofitInterface getApi() {
        return retrofit.create(RetrofitInterface.class);
    }


}
