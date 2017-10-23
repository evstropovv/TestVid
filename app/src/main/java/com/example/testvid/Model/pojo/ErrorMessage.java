package com.example.testvid.Model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class ErrorMessage {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private String error;

    public String getCode(){ return code;}

    public String getError(){ return error;}

    public Boolean getStatus() {
        return status;
    }

}
