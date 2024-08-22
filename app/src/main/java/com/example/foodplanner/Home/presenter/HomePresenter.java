package com.example.foodplanner.Home.presenter;

import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.Home.Repo.HomeNetworkCallback;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.view.IHome;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private HomeRepo remoteRebo;
    private IHome view;
    public HomePresenter(IHome view , HomeRepo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.view = view;
    }
    public void getAllCategories(){
        Observable<Category[]> observable = remoteRebo.getAllCategories();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> {
                            view.setAllCategories(categories);
                        }
                );
    }
    public void filterByCategory(String category){
        Observable<Meal[]> observable = remoteRebo.filterByCategoryCall(category);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.filterByCategory(meals);
                                }
                        );
    }

}
