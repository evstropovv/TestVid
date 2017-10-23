package com.example.testvid.View.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testvid.Presenter.Interfaces.IPresenterLogin;
import com.example.testvid.Presenter.PresenterLogin;
import com.example.testvid.R;
import com.example.testvid.View.Fragments.interfaces.IFragmentLogin;
import com.example.testvid.View.MainActivity;


public class FragmentLogin extends Fragment implements IFragmentLogin{

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ProgressBar loginProgressBar;
    private IPresenterLogin presenter;
    public FragmentLogin() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        loginProgressBar = (ProgressBar)view.findViewById(R.id.progressLogin);
        presenter = new PresenterLogin(this);

        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(etUsername.getText().toString().matches("")) && !(etPassword.getText().toString().matches(""))) {
                    loginProgressBar.setVisibility(View.VISIBLE);
                    presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.login_password_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    @Override
    public void loginResult(Boolean result) {
        loginProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getResources().getString(R.string.login_password_empty), Toast.LENGTH_SHORT).show();
        if (result) {
            Toast.makeText(getActivity(), getResources().getString(R.string.login_success), Toast.LENGTH_LONG).show();
            ((MainActivity)getActivity()).refreshPagerAdapter();
        }
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

}
