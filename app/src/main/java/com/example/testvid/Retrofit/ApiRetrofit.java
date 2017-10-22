package com.example.testvid.Retrofit;

import com.example.testvid.Model.pojo.New.NewResponse;
import com.example.testvid.Model.pojo.auth.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRetrofit {

    @GET("/videos/featured")
    Call<NewResponse> getFeatured(@Query("offset") int offset,
                                  @Query("limit") int limit);

    @GET("/videos/new")
    Call<NewResponse> getNew(@Query("offset") int offset,
                        @Query("limit") int limit);

    @GET("/videos/feed")
    Call<NewResponse> getFeed(@Query("offset") int offset,
                         @Query("limit") int limit,
                         @Query("token") String token);


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/auth/create")
    Call<AuthResponse> login(@Field("username") String username, @Field("password") String password);

}
