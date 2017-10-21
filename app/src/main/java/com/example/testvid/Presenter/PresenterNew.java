package com.example.testvid.Presenter;

import android.util.Log;

import com.example.testvid.Model.Interfaces.IModelNew;
import com.example.testvid.Model.ModelNew;
import com.example.testvid.Presenter.Interfaces.IPresenterNew;
import com.example.testvid.View.Fragments.interfaces.IFragmentNew;
import com.example.testvid.pojo.New.NewResponse;
import com.example.testvid.pojo.New.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterNew implements IPresenterNew {

    private IFragmentNew fragment;
    private IModelNew model;
    private List<Video> videos;

    int limit = 10;
    int offset = 0;

    public PresenterNew(IFragmentNew fragment) {
        this.fragment = fragment;
        model = new ModelNew();
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
