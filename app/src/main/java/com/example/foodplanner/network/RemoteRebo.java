package com.example.foodplanner.network;

import com.example.foodplanner.network.model.Categories;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public interface RemoteRebo {
    Observable<Meal[]> getAllMealsCall();
    void getMealByNameCall();
    Observable<Meal> getMealByIdCall(String id);
    void getRandomMealCall();
    void getTenRandomMealsCall();
    void listAllCategoriesCall();
    void listAllAreasCall();
    void listAllIngredientsCall();
    void filterByIngredientCall();
    void filterByAreaCall();
    Observable<Meal[]> filterByCategoryCall(String category);

    Observable<Category[]> getAllCategories();
}
