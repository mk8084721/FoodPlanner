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

import com.example.foodplanner.Home.view.RecyclerAdapter;
import com.example.foodplanner.R;
import com.example.foodplanner.network.model.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FavoriteFragment extends Fragment {

    List<Meal> meals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meals = new ArrayList<>();
        Meal meal= new Meal();
        meal.setStrMeal("meal1");
        meal.setStrMealThumb("https://www.themealdb.com/images/media/meals/xxyupu1468262513.jpg");
        meal.setIdMeal("meal1");
        meals.add(meal);
        meals.add(meal);
        meals.add(meal);
        meals.add(meal);
        meals.add(meal);
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
        RecyclerAdapter adapter = new RecyclerAdapter(meals, view.getContext());
        recyclerView.setAdapter(adapter);
    }
}