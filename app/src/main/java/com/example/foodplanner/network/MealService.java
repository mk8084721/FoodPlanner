package com.example.foodplanner.network;

import com.example.foodplanner.network.model.Categories;
import com.example.foodplanner.network.model.Meals;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("search.php?f=a")
    Observable<Meals> getAllMeals();
    @GET("search.php")
    Observable<Meals>getMealByName(@Query("s") String name);
    @GET("lookup.php")
    Observable<Meals>getMealById(@Query("i") String id);
    @GET("random.php")
    Observable<Meals>getRandomMeal();
    @GET("randomselection.php")
    Observable<Meals>getTenRandomMeals();
    @GET("list.php?c=list")
    Observable<Meals>listAllCategories();
    @GET("list.php?a=list")
    Observable<Meals>listAllAreas();
    @GET("list.php?i=list")
    Observable<Meals>listAllIngredients();
    @GET("filter.php")
    Observable<Meals>filterByIngredient(@Query("i")String ingredient);
    @GET("filter.php")
    Observable<Meals>filterByArea(@Query("a")String area);
    @GET("filter.php")
    Observable<Meals>filterByCategory(@Query("c")String category);


    @GET("categories.php")
    Observable<Categories>getAllCategories();




}
