package com.example.foodplanner.Register.Repo;

import android.content.Context;

import com.example.foodplanner.Home.Repo.HomeRepoImpl;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.model.MyUser;
import com.example.foodplanner.model.PlanMeal;

public class RegisterRepoImpl implements RegisterRepo{
    private LocalRepo localRebo;
    private static RegisterRepoImpl LocalDataSource = null;
    private static String EMAIL;
    private RegisterRepoImpl(Context context) {
        localRebo = LocalRepoImpl.getInstance(context);
        EMAIL = localRebo.readEmailShP(context);
    }

    public static RegisterRepoImpl getInstance(Context context) {
        if(LocalDataSource==null){
            LocalDataSource = new RegisterRepoImpl(context);
        }
        return LocalDataSource;
    }

    @Override
    public void insertPlanMeal(PlanMeal planMeal) {
        localRebo.insertPlanMeal(EMAIL , planMeal);
    }

    @Override
    public void insertEmptyUserPlan(String email) {
        localRebo.insertUserPlan(new MyUser(email , "0000000"));
    }
}
