package com.example.foodplanner.Home.presenter;

import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.Home.Repo.HomeNetworkCallback;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.view.IHome;

public class HomePresenter implements HomeNetworkCallback {
    HomeRepo remoteRebo;
    IHome view;
    public HomePresenter(IHome view , HomeRepo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.view = view;
    }
    public void getAllCategories(){
        remoteRebo.getAllCategories(this);
    }
    public void filterByCategory(String category){
        remoteRebo.filterByCategoryCall(this , category);
    }


    @Override
    public void onResponse(Meal[] meals) {
        view.filterByCategory(meals);
    }

    @Override
    public void onCategoryResponse(Category[] categories) {
        view.setAllCategories(categories);
    }

    @Override
    public void onFailure(String errorMsg) {

    }
}
