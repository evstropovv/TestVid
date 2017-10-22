package com.example.testvid.Presenter;


import com.example.testvid.Model.Interfaces.IModelFeautered;
import com.example.testvid.Model.ModelFeautered;
import com.example.testvid.Presenter.Interfaces.IPresenterFeautered;
import com.example.testvid.View.Fragments.interfaces.IFragmentFeautered;
import com.example.testvid.Model.pojo.New.NewResponse;
import com.example.testvid.Model.pojo.New.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFeautered implements IPresenterFeautered {

    private IFragmentFeautered fragment;
    private IModelFeautered model;
    private List<Video> videos;

    int limit = 10;
    int offset = 0;
    public PresenterFeautered(IFragmentFeautered fragment) {
        this.fragment = fragment;
        model = new ModelFeautered();
        videos = new ArrayList<>();
    }

    @Override
    public void getNewVideos() {
        model.getNewVideos(offset, limit).enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                offset += limit;
                videos.addAll(response.body().getVideos());
                fragment.showVideoList(videos);
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                fragment.showError(t.getMessage());
            }
        });

    }
}
