package com.example.foodplanner.Favorite.presenter;

import com.example.foodplanner.Favorite.Repo.FavoriteRepo;
import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.Favorite.view.IFavoriteFragment;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter {
    private FavoriteRepo localRepo;
    private IFavoriteFragment view;
    public FavoritePresenter(IFavoriteFragment view , FavoriteRepo localRepo) {
        this.view = view;
        this.localRepo = localRepo;
    }

    public void removeFromFavorite(FavoriteMeal meal) {
        localRepo.removeFromFavorite(meal);
    }

    public void getAllMeals(){
        Flowable<List<FavoriteMeal>> flowable= localRepo.getFavoriteMeals();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoriteMeals -> view.setMealsList(favoriteMeals)
                );
    }
}
