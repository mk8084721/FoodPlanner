package com.example.foodplanner.Home.view;

import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

public interface IHome {
    public void setAllCategories(Category[] categories);
    public void filterByCategory(Meal[] meals);


}
