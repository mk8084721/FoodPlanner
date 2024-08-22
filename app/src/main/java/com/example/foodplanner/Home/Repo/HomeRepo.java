package com.example.foodplanner.Home.Repo;

import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.core.Observable;

public interface HomeRepo {
    Observable<Category[]> getAllCategories();

    Observable<Meal[]> filterByCategoryCall(String category);
}
