package com.example.testvid.Presenter;


import com.example.testvid.Model.Interfaces.IModelFeed;
import com.example.testvid.Model.ModelFeed;
import com.example.testvid.Other.Preferences;
import com.example.testvid.Presenter.Interfaces.IPresenterFeed;
import com.example.testvid.View.Fragments.interfaces.IFragmentFeed;
import com.example.testvid.Model.pojo.New.NewResponse;
import com.example.testvid.Model.pojo.New.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFeed implements IPresenterFeed {

    private IFragmentFeed fragment;
    private IModelFeed model;
    private List<Video> videos;
    int limit = 10;
    int offset = 0;

    public PresenterFeed(IFragmentFeed fragment) {
        this.fragment = fragment;
        model = new ModelFeed();
        videos = new ArrayList<>();
    }

    @Override
    public void getNewVideos() {
        String token = Preferences.getToken(); //get saving token

        model.getNewVideos(offset, limit, token).enqueue(new Callback<NewResponse>() {
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
