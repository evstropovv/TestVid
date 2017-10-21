package com.example.testvid.View.Fragments.interfaces;


import com.example.testvid.pojo.New.Video;

import java.util.List;

public interface IFragmentFeautered {
    void showVideoList(List<Video> videos);
    void showError(String msg);
}

