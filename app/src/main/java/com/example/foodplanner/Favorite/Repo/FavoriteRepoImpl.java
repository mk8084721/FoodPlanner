package com.example.foodplanner.Favorite.Repo;

import android.content.Context;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.Home.Repo.HomeLocalRepoImpl;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavoriteRepoImpl implements FavoriteRepo{
    private LocalRepo localRepo;
    private static FavoriteRepoImpl LocalDataSource = null;

    private FavoriteRepoImpl(Context context) {
        localRepo = LocalRepoImpl.getInstance(context);
    }

    public static FavoriteRepoImpl getInstance(Context context) {
        if(LocalDataSource==null){
            LocalDataSource = new FavoriteRepoImpl(context);
        }
        return LocalDataSource;
    }
    @Override
    public void removeFromFavorite(FavoriteMeal meal) {
        localRepo.deleteFavoriteMeal(meal);
    }

    @Override
    public Flowable<List<FavoriteMeal>> getFavoriteMeals() {
        return localRepo.getFavoriteMeals();
    }
}
