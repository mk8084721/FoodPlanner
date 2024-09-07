package com.example.foodplanner.Home.Repo;

import android.content.Context;

import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.network.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomeLocalRepo {
    public void insertFavorite(FavoriteMeal meal);
    public void deleteFavorite(FavoriteMeal meal);

    Flowable<List<FavoriteMeal>> getFavoriteMeals();

    void updatePlanMeal(Meal meal, int selectedDay);

    String readEmailShP(Context context);
}
