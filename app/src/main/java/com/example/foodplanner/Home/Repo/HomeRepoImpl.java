package com.example.foodplanner.Home.Repo;

import android.util.Log;

import com.example.foodplanner.network.MealService;
import com.example.foodplanner.network.model.Categories;
import com.example.foodplanner.network.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRepoImpl implements HomeRepo {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealService mealService;
    private static HomeRepoImpl RemoteDataSource = null;

    private HomeRepoImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        mealService = retrofit.create(MealService.class);
    }

    public static HomeRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new HomeRepoImpl();
        }
        return RemoteDataSource;
    }

    @Override
    public void getAllCategories(HomeNetworkCallback homeNetworkCallback) {
        Call<Categories> call = mealService.getAllCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.isSuccessful()){
                    Log.i("TAG", "onResponse: "+response.body().toString());
                    homeNetworkCallback.onCategoryResponse(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable throwable) {
                Log.i("TAG", "onFailure : Callback");
                homeNetworkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void filterByCategoryCall(HomeNetworkCallback homeNetworkCallback, String category) {
        Call<Meals> call = mealService.filterByCategory(category);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()){
                    Log.i("TAG", "onResponse: "+response.body().toString());
                    homeNetworkCallback.onResponse(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                Log.i("TAG", "onFailure : Callback");
                homeNetworkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
