package com.example.testvid.Model;


import com.example.testvid.Model.Interfaces.IModelNew;
import com.example.testvid.Retrofit.ApiModule;
import com.example.testvid.pojo.New.NewResponse;

import retrofit2.Call;

public class ModelNew implements IModelNew {

    @Override
    public Call<NewResponse> getNewVideos(Integer offset, Integer limit) {
        return ApiModule.getClient().getNew(offset, limit);
    }
}
