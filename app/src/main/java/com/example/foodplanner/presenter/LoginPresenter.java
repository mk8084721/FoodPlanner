package com.example.foodplanner.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.model.User;
import com.example.foodplanner.view.ILogin;

import java.util.List;

public class LoginPresenter {
    LocalRepo localRepo;
    ILogin view;

    public LoginPresenter(LocalRepo localRepo, ILogin view) {
        this.localRepo = localRepo;
        this.view = view;
    }
    void checkAuth(User user){
        LiveData<User> userLiveData = localRepo.getUser(user.getEmail());
        userLiveData.observe((LifecycleOwner) view, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                boolean islogedin;
                if (user==null) islogedin=false;
                else islogedin=true;
                view.loginStatus(islogedin);
            }
        });
    }
}
