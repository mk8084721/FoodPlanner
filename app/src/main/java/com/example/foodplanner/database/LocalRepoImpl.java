package com.example.foodplanner.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.model.User;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalRepoImpl implements LocalRepo{
    private FoodPlannerDao dao;
    private static LocalRepoImpl localSource;

    private LocalRepoImpl(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        dao = dataBase.getFoodPlannerDao();
    }
    public static LocalRepoImpl getInstance(Context context){
        if(localSource==null){
            localSource = new LocalRepoImpl(context);
        }
        return localSource;
    }

/*    public void insertUser(User... user) {
        new Thread(){
            @Override
            public void run() {
                dao.insertUser(user);
            }
        }.start();

    }


    public void updateUser(User... user) {
        dao.updateUser(user);
    }


    public void deleteUser(User... user) {
        dao.deleteUser(user);
    }


    public LiveData<User> getUser(String email) {
        return dao.getUser(email);
    }


    public LiveData<User> userAuth(String email, String password) {
        return dao.userAuth(email, password);
    }*/

    @Override
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal) {
        new Thread(){
            @Override
            public void run() {
                dao.insertFavoriteMeal(favoriteMeal);
            }
        }.start();

    }

    @Override
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal) {
        dao.updateFavoriteMeal(favoriteMeal);
    }

    @Override
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteFavoriteMeal(favoriteMeal);
            }
        }.start();

    }

    ///////////plan

    @Override
    public Flowable<List<FavoriteMeal>> getFavoriteMeals() {
        return dao.getFavoriteMeals();
    }

    @Override
    public Flowable<List<PlanMeal>> getPlanMeals() {
        return dao.getPlanMeals();
    }

    @Override
    public Flowable<PlanMeal> getPlanMealByDayId(String dayId) {
        return dao.getPlanMealByDayId(dayId);
    }

    @Override
    public void insertPlanMeal(PlanMeal... planMeals) {
        Flowable<List<PlanMeal>> flowable = getPlanMeals();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planMeals1 -> {
                            if(planMeals1.size()<2){
                                new Thread(){
                                    @Override
                                    public void run() {
                                        dao.insertPlanMeal(planMeals);
                                    }
                                }.start();
                            }
                        }
                );
    }

    @Override
    public void updatePlanMeal(PlanMeal... planMeals) {
        new Thread(){
            @Override
            public void run() {
                dao.updatePlanMeal(planMeals);
            }
        }.start();

    }

    @Override
    public void deletePlanMeal(PlanMeal... planMeals) {
        new Thread(){
            @Override
            public void run() {
                dao.deletePlanMeal(planMeals);

            }
        }.start();
    }

    ///////////ShP

    @Override
    public String readPlanShP(Activity activity){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        return storage.getString("plan","0000000");
    }

    @Override
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString) {
        return dao.getMealsInPlan(emptyString);
    }

    @Override
    public void writePlanShP(Activity activity , String plan){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        editor.putString("plan",plan);
        editor.commit();
    }
}
