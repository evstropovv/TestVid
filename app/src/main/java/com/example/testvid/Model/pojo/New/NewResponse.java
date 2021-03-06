package com.example.testvid.Model.pojo.New;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;

    @SerializedName("viewerVotes")
    @Expose
    private List<Object> viewerVotes = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Object> getViewerVotes() {
        return viewerVotes;
    }

    public void setViewerVotes(List<Object> viewerVotes) {
        this.viewerVotes = viewerVotes;
    }

}
