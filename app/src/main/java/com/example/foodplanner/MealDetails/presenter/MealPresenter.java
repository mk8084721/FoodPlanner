package com.example.foodplanner.MealDetails.presenter;

import com.example.foodplanner.Home.Repo.HomeNetworkCallback;
import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.Home.view.IHome;
import com.example.foodplanner.MealDetails.Repo.MealNetworkCallback;
import com.example.foodplanner.MealDetails.Repo.MealRepo;
import com.example.foodplanner.MealDetails.view.IMealDetails;
import com.example.foodplanner.network.model.Meal;

public class MealPresenter implements MealNetworkCallback {
    MealRepo remoteRebo;
    IMealDetails view;
    public MealPresenter(IMealDetails view , MealRepo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.view = view;
    }
    public void getMealWithId(int id){
        remoteRebo.getMealById(this,id);
    }

    @Override
    public void onMealResponse(Meal meal) {
        view.setMealById(meal);
    }

    @Override
    public void onFailure(String errorMsg) {

    }
}
