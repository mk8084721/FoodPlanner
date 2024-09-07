package com.example.foodplanner.database;

import android.app.Activity;
import android.content.Context;

import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.model.MyUser;
import com.example.foodplanner.model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalRepo {
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();

    //plan

    public Flowable<List<PlanMeal>> getPlanMeals(String email);
    public Flowable<PlanMeal> getPlanMealByDayId(String dayId);
    public void insertPlanMeal(String email, PlanMeal... planMeals);
    public void updatePlanMeal(PlanMeal... planMeals);
    public void deletePlanMeal(PlanMeal... planMeals);
    public void writePlanShP(Activity activity , String plan);
    public String readPlanShP(Activity activity);
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString);

    public String readEmailShP(Context context);
    void rmvLocalData(Activity activity ,Context context);
    void insertUserPlan(MyUser userPlan);

    Flowable<List<MyUser>> getUserPlan(String email);
}
