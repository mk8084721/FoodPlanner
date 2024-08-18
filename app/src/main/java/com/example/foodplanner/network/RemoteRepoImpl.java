package com.example.foodplanner.network;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.foodplanner.network.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRepoImpl implements RemoteRebo{
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealService productService;
    private static RemoteRepoImpl RemoteDataSource = null;

    private RemoteRepoImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        productService = retrofit.create(MealService.class);
    }

    public static RemoteRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new RemoteRepoImpl();
        }
        return RemoteDataSource;
    }
    @Override
    public void makeNetworkCall(NetworkCallback networkCallback){
        Call<Meals> call = productService.getAllMeals();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()){
                    Log.i("TAG", "onResponse: "+response.body().toString());
                    networkCallback.onResponse(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                Log.i("TAG", "onFailure : Callback");
                networkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

}
