package com.example.foodplanner.MealDetails.Repo;

import com.example.foodplanner.network.model.Meal;

public interface MealNetworkCallback {
    public void onMealResponse(Meal Meal);
    public void onFailure(String errorMsg);
}
