package com.example.foodplanner.Search.Repo;

import com.example.foodplanner.network.RemoteRebo;
import com.example.foodplanner.network.RemoteRepoImpl;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.network.model.Meals;

import io.reactivex.rxjava3.core.Observable;

public class SearchRemoteRepoImpl implements SearchRemoteRepo{
    private RemoteRebo remoteRebo;
    private static SearchRemoteRepoImpl RemoteDataSource = null;

    private SearchRemoteRepoImpl() {
        remoteRebo = RemoteRepoImpl.getInstance();
    }

    public static SearchRemoteRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new SearchRemoteRepoImpl();
        }
        return RemoteDataSource;
    }

    @Override
    public Observable<Meals> filterByArea(String area) {
        return remoteRebo.filterByArea(area);
    }

    @Override
    public Observable<Meals> filterByIngredient(String ingredient) {
        return remoteRebo.filterByIngredient(ingredient);
    }

    @Override
    public Observable<Meal[]> filterByCategory(String category) {
        return remoteRebo.filterByCategoryCall(category);
    }

    @Override
    public Observable<Meals> getMealByName(String mealName) {
        return remoteRebo.getMealByName(mealName);
    }
}
