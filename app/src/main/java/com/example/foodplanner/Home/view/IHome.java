package com.example.foodplanner.Home.view;

import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

import java.util.Map;

public interface IHome {
    public void setAllCategories(Category[] categories);
    public void filterByCategory(Map<String , Meal[]> mealMap);
}
