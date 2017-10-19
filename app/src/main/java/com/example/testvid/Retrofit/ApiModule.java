package com.example.testvid.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiModule {

    private static ApiRetrofit apiRetrofit;

    public static ApiRetrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        if (apiRetrofit==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.vid.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiRetrofit = retrofit.create(ApiRetrofit.class);
        }
        return apiRetrofit;
    }
}
