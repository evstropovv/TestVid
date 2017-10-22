package com.example.testvid.Model.pojo.New;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("complete_url")
    @Expose
    private String completeUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("version")
    @Expose
    private Integer version;

    @SerializedName("moderated")
    @Expose
    private Boolean moderated;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;

    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;

    public String getCompleteUrl() {return completeUrl;}


    public String getTitle() {
        return title;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }



    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    public Integer getLikesCount() {
        return likesCount;
    }




}
