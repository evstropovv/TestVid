package com.example.testvid.Model.pojo.auth;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("auth")
    @Expose
    private Auth auth;

    @SerializedName("user")
    @Expose
    private User user;

    public String getCode(){ return code;}
    public String getError(){ return error;}

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}