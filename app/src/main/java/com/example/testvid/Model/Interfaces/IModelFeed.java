package com.example.testvid.Model.Interfaces;


import com.example.testvid.Model.pojo.New.NewResponse;

import retrofit2.Call;

public interface IModelFeed {
    Call<NewResponse> getNewVideos(Integer offset, Integer limit, String token);

}
