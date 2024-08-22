package com.example.foodplanner.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.Login.presenter.LoginPresenter;


public class LoginFragment extends Fragment implements ILogin {
    LoginPresenter presenter;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(LocalRepoImpl.getInstance(getContext()),this);
        presenter.insertUser();
        String id = presenter.readSharedPreferance(getActivity());
        if(id!=null){
            Intent intent = new Intent(this.getContext(), HomeActivity.class);
            intent.putExtra("userId",id);
            startActivity(intent);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        Button loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailTxt = view.findViewById(R.id.emailTxt);
                EditText passwordTxt = view.findViewById(R.id.passwordTxt);
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();

//                presenter.checkAuth(new User(email,password));
            }
        });
    }

    /*@Override
    public void loginStatus(boolean isLogedin , Long id) {
        if(isLogedin){
            presenter.writeInSharedPreferance(getActivity(),1,id);
            Intent intent = new Intent(this.getContext(), HomeActivity.class);
            intent.putExtra("userId",id);
            startActivity(intent);
        }else{
            Toast.makeText(view.getContext(), "wrong Email / password", Toast.LENGTH_SHORT).show();
        }
    }*/
}