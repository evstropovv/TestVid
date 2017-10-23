package com.example.testvid.Presenter;


import android.util.Log;

import com.example.testvid.Model.Interfaces.IModelLogin;
import com.example.testvid.Model.ModelLogin;
import com.example.testvid.Model.pojo.ErrorMessage;
import com.example.testvid.Other.Preferences;
import com.example.testvid.Presenter.Interfaces.IPresenterLogin;
import com.example.testvid.Other.Variables;
import com.example.testvid.View.Fragments.interfaces.IFragmentLogin;
import com.example.testvid.Model.pojo.auth.AuthResponse;
import com.google.gson.Gson;

import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogin implements IPresenterLogin {
    private IFragmentLogin fragment;
    private IModelLogin modelLogin;


    public PresenterLogin(IFragmentLogin fragmentLogin) {
        this.fragment = fragmentLogin;
        modelLogin = new ModelLogin();
    }

    @Override
    public void login(String username, String password) {
        modelLogin.login(username, password).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                if (response.isSuccessful()) {
                    try {
                        Preferences.setToken(response.body().getAuth().getToken());
                        Variables.isLogin = true;
                        fragment.loginResult(true);
                    } catch (NullPointerException e) {
                        Variables.isLogin = false;
                        Preferences.setToken(null);
                        fragment.loginResult(false);
                    }
                } else {
                    Gson gson = new Gson();
                    ErrorMessage message = gson.fromJson(response.errorBody().charStream(), ErrorMessage.class);
                    Variables.isLogin = false;
                    Preferences.setToken(null);
                    fragment.loginError(message.getError());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                fragment.loginResult(false);
                Variables.isLogin = false;
                Preferences.setToken(null);
            }
        });
    }

}
