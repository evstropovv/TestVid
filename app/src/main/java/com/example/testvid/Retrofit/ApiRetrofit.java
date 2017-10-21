package com.example.testvid.Retrofit;

import com.example.testvid.pojo.New.NewResponse;
import com.example.testvid.pojo.auth.AuthResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRetrofit {

    @GET("/videos/featured")
    Call<NewResponse> getFeatured(@Query("offset") int offset,
                                  @Query("limit") int limit);

    @GET("/videos/new")
    Call<NewResponse> getNew(@Query("offset") int offset,
                        @Query("limit") int limit);

    @GET("/videos/feed")
    Call<String> getFeed(@Query("offset") int offset,
                         @Query("limit") int limit,
                         @Query("token") String token);


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/auth/create")
    Call<AuthResponse> login(@Field("username") String username, @Field("password") String password);

}
