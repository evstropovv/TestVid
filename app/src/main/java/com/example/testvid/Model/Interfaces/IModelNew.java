package com.example.testvid.Model.Interfaces;

import com.example.testvid.Model.pojo.New.NewResponse;

import retrofit2.Call;

public interface IModelNew {

    Call<NewResponse> getNewVideos(Integer offset, Integer limit);

}
