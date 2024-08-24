package com.example.foodplanner.Search.Repo;

import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.network.model.Meals;

import io.reactivex.rxjava3.core.Observable;

public interface SearchRemoteRepo {
    Observable<Meals> filterByArea(String area);

    Observable<Meals> filterByIngredient(String ingredient);

    Observable<Meal[]> filterByCategory(String category);

    Observable<Meals> getMealByName(String mealName);
}
