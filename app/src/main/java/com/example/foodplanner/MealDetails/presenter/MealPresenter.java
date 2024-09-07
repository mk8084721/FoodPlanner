package com.example.foodplanner.MealDetails.presenter;

import com.example.foodplanner.Favorite.Repo.FavoriteRepo;
import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.MealDetails.Repo.MealRepo;
import com.example.foodplanner.MealDetails.view.IMealDetails;
import com.example.foodplanner.network.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPresenter {
    MealRepo remoteRebo;
    FavoriteRepo favoriteRepo;
    IMealDetails view;
    public MealPresenter(IMealDetails view ,FavoriteRepo favoriteRepo, MealRepo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.favoriteRepo = favoriteRepo;
        this.view = view;
    }
    public void getMealWithId(String id){
        Observable<Meal> observable = remoteRebo.getMealById(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> getFavoriteMeals(meal));
    }
    public void getFavoriteMeals(Meal meal){
        Flowable<List<FavoriteMeal>> flowable = favoriteRepo.getFavoriteMeals();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoriteMeals -> {
                            for (int i = 0; i<favoriteMeals.size() ; i++){
                                    if(meal.getIdMeal().equals(favoriteMeals.get(i).getMealId())){
                                        meal.setFavorite(true);
                                        break;
                                    }
                            }
                        view.setMealById(meal);
                        }
                );
    }

}
