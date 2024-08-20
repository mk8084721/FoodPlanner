package com.example.foodplanner.MealDetails.Repo;

public interface MealRepo {
    public void getMealById(MealNetworkCallback homeNetworkCallback, int id);
}
