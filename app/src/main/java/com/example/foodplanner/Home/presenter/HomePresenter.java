package com.example.foodplanner.Home.presenter;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.Home.Repo.HomeLocalRepo;
import com.example.foodplanner.Home.Repo.HomeRepo;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.view.IHome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private HomeRepo remoteRepo;
    private HomeLocalRepo localRepo;
    private IHome view;
    private Map<String,Meal[]> mealMap;
    public HomePresenter(IHome view , HomeRepo homeRepo , HomeLocalRepo localRepo) {
        this.remoteRepo = homeRepo;
        this.localRepo = localRepo;
        this.view = view;
        mealMap = new HashMap<>();
    }
    public void getAllCategories(){
        Observable<Category[]> observable = remoteRepo.getAllCategories();
        observable.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3).subscribe(
                        categories -> {
                            view.setAllCategories(categories);
                        },
                throwable -> throwable.printStackTrace()
                );
    }
    public boolean filterByCategory(String category){
        AtomicBoolean errorCategroy = new AtomicBoolean(false);
        Observable<Meal[]> observable = remoteRepo.filterByCategoryCall(category);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry(3).subscribe(
                                meals -> {
                                    getFavoriteMeals(meals,category);
                                },
                        throwable -> {
                                    errorCategroy.set(true);
                                    throwable.printStackTrace();
                                }
                        );
        return errorCategroy.get();
    }

    public void getFavoriteMeals(Meal[] meals, String category){
        Flowable<List<FavoriteMeal>> flowable = localRepo.getFavoriteMeals();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoriteMeals -> {
                            for (int i = 0; i<favoriteMeals.size() ; i++){
                                for (int j=0 ; j< meals.length ; j++){
                                    if(meals[j].getIdMeal().equals(favoriteMeals.get(i).getMealId())){
                                        meals[j].setFavorite(true);
                                        break;
                                    }
                                }
                            }
                            mealMap.put(category,meals);
                            view.filterByCategory(mealMap);
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
