package com.example.foodplanner.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
    /*public void checkAuth(User user){
        LiveData<User> userLiveData = localRepo.userAuth(user.getEmail(), user.getPassword());
        userLiveData.observe((LifecycleOwner) view, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                boolean islogedin;
                if (user==null) islogedin=false;
                else islogedin=true;
                view.loginStatus(islogedin, user.getId());
            }
        });
    }*/
    public String readSharedPreferance(Activity activity){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        int loginInt = storage.getInt("isLogedin",0);
        if(loginInt==0){
            return null;
        }else{
            String email = storage.getString("userEmail", null);
            return email;
        }
    }
    public void writeInSharedPreferance(Activity activity ,int isLogedin , String email){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        editor.putInt("isLogedin",isLogedin);
        editor.putString("userEmail",email);
        editor.commit();
    }

    public void insertUser() {
        localRepo.insertUser(new User("m@k.com","mohamed","11"));
    }
}
