package com.example.testvid.View.Fragments.interfaces;


import com.example.testvid.Model.pojo.New.Video;

import java.util.List;

public interface IFragmentFeed {
    void showVideoList(List<Video> videoList);
    void showError(String msg);
}
