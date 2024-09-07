package com.example.foodplanner.Favorite.view;

import com.example.foodplanner.model.FavoriteMeal;

import java.util.List;

public interface FavoriteOnClickListener {
    public void removeMealFromFavorite(List<FavoriteMeal> meals ,FavoriteMeal meal);
}
