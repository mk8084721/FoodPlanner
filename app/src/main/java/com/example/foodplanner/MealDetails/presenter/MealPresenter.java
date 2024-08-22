package com.example.foodplanner.MealDetails.presenter;

import com.example.foodplanner.MealDetails.Repo.MealRepo;
import com.example.foodplanner.MealDetails.view.IMealDetails;
import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPresenter {
    MealRepo remoteRebo;
    IMealDetails view;
    public MealPresenter(IMealDetails view , MealRepo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.view = view;
    }
    public void getMealWithId(String id){
        Observable<Meal> observable = remoteRebo.getMealById(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> view.setMealById(meal));
    }

}
