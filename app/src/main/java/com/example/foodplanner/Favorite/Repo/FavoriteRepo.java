package com.example.foodplanner.Favorite.Repo;

import com.example.foodplanner.Favorite.model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriteRepo {
    public void removeFromFavorite(FavoriteMeal meal);
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();
}
