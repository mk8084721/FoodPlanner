package com.example.foodplanner.Favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Favorite.Repo.FavoriteRepoImpl;
import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.Favorite.presenter.FavoritePresenter;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteOnClickListener,IFavoriteFragment {

    List<FavoriteMeal> meals;
    FavoriteAdapter adapter;
    FavoritePresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter= new FavoritePresenter(this ,FavoriteRepoImpl.getInstance(getContext()));
        presenter.getAllMeals();
        meals=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.favRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new FavoriteAdapter(meals, this ,view.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void removeMealFromFavorite(List<FavoriteMeal> meals, FavoriteMeal meal) {
        presenter.removeFromFavorite(meal);
        meals.remove(meal);
        adapter.setMealsList(meals);

    }

    @Override
    public void setMealsList(List<FavoriteMeal> favoriteMeals) {
        adapter.setMealsList(favoriteMeals);
    }
}