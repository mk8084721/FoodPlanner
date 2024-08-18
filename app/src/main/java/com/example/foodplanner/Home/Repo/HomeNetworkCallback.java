package com.example.foodplanner.Home.Repo;

import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

public interface HomeNetworkCallback {
    public void onResponse(Meal[] Meals);
    public void onCategoryResponse(Category[] categories);
    public void onFailure(String errorMsg);
}
