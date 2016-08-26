package com.youzh.xiaowuxiang.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by youzehong on 16/6/7.
 */
public interface MovieApi {

    @GET("/")
    Call<String> getMovieList();

    @GET("/page/{page}")
    Call<String> getMovieListPage(@Path("page") String page);

    @GET("/movie/{number}")
    Call<String> getMovieDetail(@Path("number") String number);
}
