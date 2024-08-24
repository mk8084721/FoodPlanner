package com.example.foodplanner.network;

import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.network.model.Meals;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRepoImpl implements RemoteRebo{
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealService mealService;
    private static RemoteRepoImpl RemoteDataSource = null;

    private RemoteRepoImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mealService = retrofit.create(MealService.class);
    }

    public static RemoteRepoImpl getInstance() {
        if(RemoteDataSource==null){
            RemoteDataSource = new RemoteRepoImpl();
        }
        return RemoteDataSource;
    }

   /* public Observable<Meal[]> getAllMealsCall(HomeNetworkCallback homeNetworkCallback){
        Observable<Meals> call = productService.getAllMeals();
        return call.map(meals -> meals.getMeals());
//        call.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful()){
//                    Log.i("TAG", "onResponse: "+response.body().toString());
//                    homeNetworkCallback.onResponse(response.body().getMeals());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable throwable) {
//                Log.i("TAG", "onFailure : Callback");
//                homeNetworkCallback.onFailure(throwable.getMessage());
//                throwable.printStackTrace();
//            }
//        });
    }*/

    @Override
    public Observable<Meal[]> getAllMealsCall() {
        Observable<Meals> call = mealService.getAllMeals();
        return call.map(meals -> meals.getMeals());
    }

    @Override
    public void getMealByNameCall() {

    }

    @Override
    public Observable<Meal> getMealByIdCall(String id) {
        return mealService.getMealById(id).map(meals -> meals.getMeals()[0]);
    }

    @Override
    public void getRandomMealCall() {

    }

    @Override
    public void getTenRandomMealsCall() {

    }

    @Override
    public void listAllCategoriesCall() {

    }

    @Override
    public void listAllAreasCall() {

    }

    @Override
    public void listAllIngredientsCall() {

    }

    @Override
    public void filterByIngredientCall() {

    }

    @Override
    public void filterByAreaCall() {

    }

    @Override
    public Observable<Meals> getMealByName(String name) {
        return mealService.getMealByName(name);
    }

    @Override
    public Observable<Meals> filterByIngredient(String ingredient) {
        return mealService.filterByIngredient(ingredient);
    }

    @Override
    public Observable<Meals> filterByArea(String area) {
        return mealService.filterByArea(area);
    }

    @Override
    public Observable<Meal[]> filterByCategoryCall(String category) {
        return mealService.filterByCategory(category).map(c -> c.getMeals());
    }

    @Override
    public Observable<Category[]> getAllCategories() {
        return mealService.getAllCategories().map(categories -> categories.getCategories());
    }
}
