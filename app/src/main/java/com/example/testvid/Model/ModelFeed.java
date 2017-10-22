package com.example.testvid.Model;

import com.example.testvid.Model.Interfaces.IModelFeed;
import com.example.testvid.Retrofit.ApiModule;
import com.example.testvid.Model.pojo.New.NewResponse;

import retrofit2.Call;

public class ModelFeed implements IModelFeed {
    @Override
    public Call<NewResponse> getNewVideos(Integer offset, Integer limit, String token) {
        return ApiModule.getClient().getFeed(offset, limit, token);
    }
}
