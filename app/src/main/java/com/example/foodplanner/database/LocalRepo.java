package com.example.foodplanner.database;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalRepo {
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();

    //plan

    public Flowable<List<PlanMeal>> getPlanMeals();
    public Flowable<PlanMeal> getPlanMealByDayId(String dayId);
    public void insertPlanMeal(PlanMeal... planMeals);
    public void updatePlanMeal(PlanMeal... planMeals);
    public void deletePlanMeal(PlanMeal... planMeals);
    public void writePlanShP(Activity activity , String plan);
    public String readPlanShP(Activity activity);
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString);

    void rmvLocalData(Activity activity ,Context context);
}
