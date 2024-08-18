package com.example.foodplanner.network;

import com.example.foodplanner.Home.Repo.HomeNetworkCallback;

public interface RemoteRebo {
    void getAllProductsCall(HomeNetworkCallback homeNetworkCallback);
    void getMealByNameCall(HomeNetworkCallback homeNetworkCallback);
    void getMealByIdCall(HomeNetworkCallback homeNetworkCallback);
    void getRandomMealCall(HomeNetworkCallback homeNetworkCallback);
    void getTenRandomMealsCall(HomeNetworkCallback homeNetworkCallback);
    void listAllCategoriesCall(HomeNetworkCallback homeNetworkCallback);
    void listAllAreasCall(HomeNetworkCallback homeNetworkCallback);
    void listAllIngredientsCall(HomeNetworkCallback homeNetworkCallback);
    void filterByIngredientCall(HomeNetworkCallback homeNetworkCallback);
    void filterByAreaCall(HomeNetworkCallback homeNetworkCallback);
    void filterByCategoryCall(HomeNetworkCallback homeNetworkCallback);

    void getAllCategories(HomeNetworkCallback homeNetworkCallback);
}
