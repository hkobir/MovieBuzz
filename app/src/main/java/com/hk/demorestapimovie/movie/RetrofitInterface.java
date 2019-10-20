package com.hk.demorestapimovie.movie;

import com.hk.demorestapimovie.pojo.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("marvel/")
    Call<List<Movie>>getData();
}
