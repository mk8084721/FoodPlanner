package com.example.foodplanner.Home.Repo;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.network.model.Meal;

public interface HomeLocalRepo {
    public void insertFavorite(FavoriteMeal meal);
    public void deleteFavorite(FavoriteMeal meal);
}
