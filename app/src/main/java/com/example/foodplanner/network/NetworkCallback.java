package com.example.foodplanner.network;

import com.example.foodplanner.network.model.Meal;

public interface NetworkCallback {
    public void onResponse(Meal[] Meals);
    public void onFailure(String errorMsg);
}
