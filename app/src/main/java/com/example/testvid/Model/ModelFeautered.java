package com.example.testvid.Model;

import com.example.testvid.Model.Interfaces.IModelFeautered;
import com.example.testvid.Retrofit.ApiModule;
import com.example.testvid.pojo.New.NewResponse;

import retrofit2.Call;

public class ModelFeautered implements IModelFeautered{
    @Override
    public Call<NewResponse> getNewVideos(Integer offset, Integer limit) {
        return ApiModule.getClient().getFeatured(offset, limit);
    }
}
