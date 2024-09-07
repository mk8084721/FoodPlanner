package com.example.foodplanner.Register.presenter;

import android.util.Log;

import com.example.foodplanner.Register.Repo.RegisterRepo;
import com.example.foodplanner.Register.Repo.RegisterRepoImpl;
import com.example.foodplanner.model.PlanMeal;

public class RegisterPresenter {
    RegisterRepo repo;

    public RegisterPresenter(RegisterRepoImpl repo) {
        this.repo = repo;
    }

    public void insertEmptyPlanDays(String email) {
        for(int i = 0 ; i<7 ; i++){
            Log.i("Register", "Register Presenter insertEmptyPlanDays: email : "+email+i);
            repo.insertPlanMeal(new PlanMeal(email , String.valueOf(i),"","",""));
        }
    }

    public void insertEmptyPlanUser(String email) {
        Log.i("Register", "Register Presenter insertEmptyPlanUser: "+email);
        repo.insertEmptyUserPlan(email);
    }
}
