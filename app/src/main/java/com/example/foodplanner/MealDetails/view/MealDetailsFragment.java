package com.example.foodplanner.MealDetails.view;

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
import com.example.foodplanner.MealDetails.model.Ingredient;
import com.example.foodplanner.R;
import com.example.foodplanner.network.model.Meal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MealDetailsFragment extends Fragment implements IMealDetails{

    View mealView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealView = view;
    }

    @Override
    public void setMealById(Meal meal) {
        Ingredient ingredient = new Ingredient(meal);
        List<String> ingredients = ingredient.getIngeredients().stream().filter(s -> (s!=null||!s.equals(""))).collect(Collectors.toList());
        List<String> measures = ingredient.getMeasures();

        RecyclerView recyclerView = mealView.findViewById(R.id.ingredientRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(mealView.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        IngredientRA adapter = new IngredientRA(ingredients,measures, mealView.getContext());
        recyclerView.setAdapter(adapter);

    }
}