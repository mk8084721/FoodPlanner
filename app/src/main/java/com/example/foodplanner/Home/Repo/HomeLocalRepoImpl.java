package com.example.foodplanner.Home.Repo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.model.PlanMeal;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.network.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeLocalRepoImpl implements HomeLocalRepo{
    private LocalRepo localRepo;
    private static HomeLocalRepoImpl LocalDataSource = null;

    private HomeLocalRepoImpl(Context context) {
        localRepo = LocalRepoImpl.getInstance(context);
    }

    public static HomeLocalRepoImpl getInstance(Context context) {
        if(LocalDataSource==null){
            LocalDataSource = new HomeLocalRepoImpl(context);
        }
        return LocalDataSource;
    }

    @Override
    public void insertFavorite(FavoriteMeal meal) {
        localRepo.insertFavoriteMeal(meal);
    }

    @Override
    public void deleteFavorite(FavoriteMeal meal) {
        localRepo.deleteFavoriteMeal(meal);
    }

    @Override
    public Flowable<List<FavoriteMeal>> getFavoriteMeals() {
        return localRepo.getFavoriteMeals();
    }

    @Override
    public void updatePlanMeal(Meal meal, int selectedDay) {
        Flowable<PlanMeal> flowable= localRepo.getPlanMealByDayId(String.valueOf(selectedDay));
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planMeal -> {
                            planMeal.setMealImg(meal.getStrMealThumb());
                            planMeal.setMealName(meal.getStrMeal());
                            planMeal.setMealId(meal.getIdMeal());
                            localRepo.updatePlanMeal(planMeal);
                        }
                );
    }

    @Override
    public String readEmailShP(Context context) {
        SharedPreferences storage = context.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        String email = storage.getString("userEmail", null);
        return email;
    }


}
