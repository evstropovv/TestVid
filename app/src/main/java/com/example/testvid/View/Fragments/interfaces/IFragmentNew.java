package com.example.testvid.View.Fragments.interfaces;


import com.example.testvid.Model.pojo.New.Video;

import java.util.List;

public interface IFragmentNew {
    void showVideoList(List<Video> videos);
    void showError(String msg);
}
