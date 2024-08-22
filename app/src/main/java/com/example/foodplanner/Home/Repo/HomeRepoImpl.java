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
        /*call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.isSuccessful()){
                    //Log.i("TAG", "onResponse: "+response.body().toString());
                    homeNetworkCallback.onCategoryResponse(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable throwable) {
                Log.i("TAG", "onFailure : Callback");
                homeNetworkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });*/
    }

    @Override
    public Observable<Meal[]> filterByCategoryCall( String category) {
        Observable<Meal[]> call = remoteRebo.filterByCategoryCall(category);
        return call;
        /*call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()){
                    //Log.i("TAG", "onResponse: "+response.body().toString());
                    homeNetworkCallback.onResponse(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                //Log.i("TAG", "onFailure : Callback");
                homeNetworkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });*/
    }
}
