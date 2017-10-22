package com.example.testvid.Model.Interfaces;


import com.example.testvid.Model.pojo.auth.AuthResponse;

import retrofit2.Call;

public interface IModelLogin {
    Call<AuthResponse> login(String username, String password);
}
