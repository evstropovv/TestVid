package com.example.testvid.Retrofit;

import com.example.testvid.pojo.auth.AuthResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiRetrofit {

    @GET("/videos/featured")
    Call<String> getFeatured(@QueryMap HashMap<String, String> map);

    @GET("/videos/new")
    Call<String> getNew(@QueryMap HashMap<String, String> map);

    @GET("/videos/feed")
    Call<String> getFeed(@QueryMap HashMap<String, String> map);


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/auth/create")
    Call<AuthResponse> login(@Field("username") String username, @Field("password") String password);

}
