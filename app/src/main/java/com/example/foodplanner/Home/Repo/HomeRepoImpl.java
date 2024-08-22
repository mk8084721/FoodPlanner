package com.example.foodplanner.Home.Repo;

import com.example.foodplanner.network.RemoteRebo;
import com.example.foodplanner.network.RemoteRepoImpl;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.core.Observable;

public class HomeRepoImpl implements HomeRepo {
    private RemoteRebo remoteRebo;
    private static HomeRepoImpl RemoteDataSource = null;

    private HomeRepoImpl() {
        remoteRebo = RemoteRepoImpl.getInstance();
    }

    public static HomeRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new HomeRepoImpl();
        }
        return RemoteDataSource;
    }

    @Override
    public Observable<Category[]> getAllCategories() {
        Observable<Category[]> call = remoteRebo.getAllCategories();
        return call;
    }

    @Override
    public Observable<Meal[]> filterByCategoryCall( String category) {
        Observable<Meal[]> call = remoteRebo.filterByCategoryCall(category);
        return call;
    }
}
