package com.example.foodplanner.Search.presenter;

import com.example.foodplanner.Home.Repo.HomeLocalRepo;
import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.Home.view.IHome;
import com.example.foodplanner.Search.Repo.SearchRemoteRepo;
import com.example.foodplanner.Search.view.ISearch;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.network.model.Meals;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {

    private SearchRemoteRepo remoteRepo;
    private ISearch view;

    public SearchPresenter(SearchRemoteRepo remoteRepo, ISearch view) {
        this.remoteRepo = remoteRepo;
        this.view = view;
    }


    public void filterByArea(String area) {
        Observable<Meals> mealsObservable = remoteRepo.filterByArea(area);
        mealsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.setSearchResult(meals.getMeals());
                        },
                        throwable -> throwable.printStackTrace()
                );
    }

    public void filterByIngredient(String ingredient) {
        Observable<Meals> mealsObservable = remoteRepo.filterByIngredient(ingredient);
        mealsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.setSearchResult(meals.getMeals());
                        },
                        throwable -> throwable.printStackTrace()
                );
    }

    public void filterByCategory(String category) {
        Observable<Meal[]> mealsObservable = remoteRepo.filterByCategory(category);
        mealsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.setSearchResult(meals);
                        },
                        throwable -> throwable.printStackTrace()
                );
    }

    public void getMealByName(String mealName) {
        Observable<Meals> mealsObservable = remoteRepo.getMealByName(mealName);
        mealsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.setSearchResult(meals.getMeals());
                        },
                        throwable -> throwable.printStackTrace()
                );
    }
}
