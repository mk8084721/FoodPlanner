package com.example.foodplanner.Login.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.unUsed.ILogin;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {
    ILogin view;
    LocalRepo localRepo;
    public LoginPresenter( ILogin view , LocalRepo localRepo) {
        this.view = view;
        this.localRepo = localRepo;
    }
   /* public void checkAuth(User user){
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
        editor.putString("plan","0000000");
        editor.commit();
    }
    public void insertEmptyPlanDays(){
        for(int i = 0 ; i<7 ; i++){
            localRepo.insertPlanMeal(new PlanMeal(String.valueOf(i),"","",""));
        }
    }

//    public void insertUser() {
//        localRepo.insertUser(new User("m@k.com","mohamed","11"));
//    }
}
