package com.example.testvid.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testvid.R;
import com.example.testvid.Retrofit.ApiModule;
import com.example.testvid.pojo.auth.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLogin extends Fragment {

    EditText etUsername, etPassword;
    Button btnLogin;

    public FragmentLogin() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(etUsername.getText().toString().matches("")) || (etPassword.getText().toString().matches(""))) {
                    login(etUsername.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "Login or Password is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void login(String name, String password) {
        Call<AuthResponse> test = ApiModule.getClient().login(name, password);

        test.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Toast.makeText(getActivity(), response.body().getUser().getEmail() + " login succesfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });

    }
}
