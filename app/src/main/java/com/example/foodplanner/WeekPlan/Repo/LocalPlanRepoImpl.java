package com.example.foodplanner.WeekPlan.Repo;

import android.app.Activity;
import android.content.Context;

import com.example.foodplanner.model.PlanMeal;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalPlanRepoImpl implements LocalPlanRepo{

    private LocalRepo localRepo;
    private static LocalPlanRepoImpl LocalDataSource = null;

    private LocalPlanRepoImpl(Context context) {
        localRepo = LocalRepoImpl.getInstance(context);
    }

    public static LocalPlanRepoImpl getInstance(Context context) {
        if(LocalDataSource==null){
            LocalDataSource = new LocalPlanRepoImpl(context);
        }
        return LocalDataSource;
    }

    @Override
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString) {
        return localRepo.getMealsInPlan(emptyString);
    }

    @Override
    public String readPlanShp(Activity activity) {
        return localRepo.readPlanShP(activity);
    }

    @Override
    public void writePlanShP(Activity activity, String plan) {
        localRepo.writePlanShP(activity,plan);
    }

    @Override
    public void updatePlanMeal(PlanMeal planMeal) {
        localRepo.updatePlanMeal(planMeal);
    }
}
