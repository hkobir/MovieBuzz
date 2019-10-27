package com.hk.demorestapimovie.movie;

import com.hk.demorestapimovie.pojo.Movie;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("marvel/")
    Call<List<Movie>>getData();


    //post data using path,query and URL

    @GET("marvel/")
    Call<List<Movie>>getMovieName(@Query("name") String mName);


    //insert data using post annotation
    @FormUrlEncoded
    @POST("marvel/")
    Call<ResponseBody> insertData(
            @Field("name") String name,
            @Field("realname") String rname,
            @Field("team") String team,
            @Field("firstappearance") String fApperance,
            @Field("createdby") String cBy,
            @Field("publisher") String publisher,
            @Field("imageurl") String url,
            @Field("bio") String bio

    );
}
