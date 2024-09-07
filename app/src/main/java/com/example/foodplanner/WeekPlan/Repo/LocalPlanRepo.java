package com.example.foodplanner.WeekPlan.Repo;

import android.app.Activity;

import com.example.foodplanner.model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalPlanRepo {
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString);

    String readPlanShp(Activity activity);

    void writePlanShP(Activity activity, String plan);

    void updatePlanMeal(PlanMeal planMeal);
}
