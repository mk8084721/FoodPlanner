package com.example.foodplanner.MealDetails.Repo;

import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.core.Observable;

public interface MealRepo {
    public Observable<Meal> getMealById(String id);
}
