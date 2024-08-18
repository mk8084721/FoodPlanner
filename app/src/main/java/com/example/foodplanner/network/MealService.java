package com.example.foodplanner.network;

import com.example.foodplanner.network.model.Categories;
import com.example.foodplanner.network.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("search.php?f=a")
    Call<Meals> getAllMeals();
    @GET("search.php")
    Call<Meals>getMealByName(@Query("s") String name);
    @GET("search.php")
    Call<Meals>getMealById(@Query("i") int id);
    @GET("random.php")
    Call<Meals>getRandomMeal();
    @GET("randomselection.php")
    Call<Meals>getTenRandomMeals();
    @GET("list.php?c=list")
    Call<Meals>listAllCategories();
    @GET("list.php?a=list")
    Call<Meals>listAllAreas();
    @GET("list.php?i=list")
    Call<Meals>listAllIngredients();
    @GET("filter.php")
    Call<Meals>filterByIngredient(@Query("i")String ingredient);
    @GET("filter.php")
    Call<Meals>filterByArea(@Query("a")String area);
    @GET("filter.php")
    Call<Meals>filterByCategory(@Query("c")String category);


    @GET("categories.php")
    Call<Categories>getAllCategories();




}
