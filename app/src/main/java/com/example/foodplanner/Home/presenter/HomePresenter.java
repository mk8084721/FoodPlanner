package com.example.foodplanner.Home.presenter;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.Home.Repo.HomeLocalRepo;
import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.view.IHome;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private HomeRepo remoteRepo;
    private HomeLocalRepo localRepo;
    private IHome view;
    public HomePresenter(IHome view , HomeRepo homeRepo , HomeLocalRepo localRepo) {
        this.remoteRepo = homeRepo;
        this.localRepo = localRepo;
        this.view = view;
    }
    public void getAllCategories(){
        Observable<Category[]> observable = remoteRepo.getAllCategories();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> {
                            view.setAllCategories(categories);
                        }
                );
    }
    public void filterByCategory(String category){
        Observable<Meal[]> observable = remoteRepo.filterByCategoryCall(category);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.filterByCategory(meals);
                                }
                        );
    }

    public void insertFavorite(Meal meal) {
        localRepo.insertFavorite(new FavoriteMeal(meal.getIdMeal(), meal.getStrMealThumb(), meal.getStrMeal()));
    }

    public void deleteFavorite(Meal meal) {
        localRepo.deleteFavorite(new FavoriteMeal(meal.getIdMeal(), meal.getStrMealThumb(), meal.getStrMeal()));
    }
}
