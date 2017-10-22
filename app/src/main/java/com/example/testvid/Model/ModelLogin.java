package com.example.testvid.Model;


import com.example.testvid.Model.Interfaces.IModelLogin;
import com.example.testvid.Retrofit.ApiModule;
import com.example.testvid.Model.pojo.auth.AuthResponse;

import retrofit2.Call;

public class ModelLogin implements IModelLogin{
    @Override
    public Call<AuthResponse> login(String username, String password) {
        return ApiModule.getClient().login(username, password);
    }
}
