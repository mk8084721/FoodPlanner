package com.example.foodplanner.MealDetails.Repo;

import android.content.Context;

import com.example.foodplanner.Favorite.Repo.FavoriteRepo;
import com.example.foodplanner.Favorite.Repo.FavoriteRepoImpl;
import com.example.foodplanner.network.RemoteRebo;
import com.example.foodplanner.network.RemoteRepoImpl;
import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.core.Observable;

public class MealRepoImpl implements MealRepo{
    private RemoteRebo remoteRebo;
    private static MealRepoImpl RemoteDataSource = null;

    private MealRepoImpl() {
       remoteRebo = RemoteRepoImpl.getInstance();
    }

    public static MealRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new MealRepoImpl();
        }
        return RemoteDataSource;
    }

    @Override
    public Observable<Meal> getMealById(String id) {
        return remoteRebo.getMealByIdCall(id);

//        Call<Meals> call = mealService.getMealById(id);
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful()){
//                    Log.i("TAG", "onResponse: "+response.body().toString());
//                    mealNetworkCallback.onMealResponse(response.body().getMeals()[0]);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable throwable) {
//                Log.i("TAG", "onFailure : Callback");
//                mealNetworkCallback.onFailure(throwable.getMessage());
//                throwable.printStackTrace();
//            }
//
//
//        });
    }

//    @Override
//    public void filterByCategoryCall(HomeNetworkCallback homeNetworkCallback, String category) {
//        Call<Meals> call = mealService.filterByCategory(category);
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful()){
//                    //Log.i("TAG", "onResponse: "+response.body().toString());
//                    homeNetworkCallback.onResponse(response.body().getMeals());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable throwable) {
//                //Log.i("TAG", "onFailure : Callback");
//                homeNetworkCallback.onFailure(throwable.getMessage());
//                throwable.printStackTrace();
//            }
//        });
//    }
}
