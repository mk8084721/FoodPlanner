package com.example.foodplanner.Home.Repo;

public interface HomeRepo {
    void getAllCategories(HomeNetworkCallback homeNetworkCallback);

    void filterByCategoryCall(HomeNetworkCallback homeNetworkCallback, String category);
}
