package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalRepo {
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();
}
