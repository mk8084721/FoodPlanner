package com.example.foodplanner.MealDetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Home.view.RecyclerAdapter;
import com.example.foodplanner.MealDetails.Repo.MealRepoImpl;
import com.example.foodplanner.MealDetails.model.Ingredient;
import com.example.foodplanner.MealDetails.presenter.MealPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.network.model.Meal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealDetailsFragment extends Fragment implements IMealDetails{

    View mealView;
    MealPresenter presenter;
    TextView mealName;
    TextView mealDetails;
    ImageView mealImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MealPresenter(this,MealRepoImpl.getInstance());
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
        String mealId = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        Log.i("TAG", "onViewCreated: "+mealId);
        presenter.getMealWithId(mealId);
        mealView = view;
        mealName = view.findViewById(R.id.MealName);
        mealDetails = view.findViewById(R.id.mealDetails);
        mealImage = view.findViewById(R.id.mealDetailsImage);
    }

    @Override
    public void setMealById(Meal meal) {
        try {
            Glide.with(mealView.getContext()).load(new URL(meal.getStrMealThumb()))
                    .apply(new RequestOptions().override(200,200)
                            .placeholder(R.drawable.place_holder)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(mealImage);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        mealName.setText(meal.getStrMeal());
        mealDetails.setText(
                "Area :\n\n"+"      "+meal.getStrArea()+"\n\n"+
                "Cateegory :\n\n"+"      "+meal.getStrCategory()+"\n\n"+
                "Instructions :\n\n"+meal.getStrInstructions()+"\n\n"
        );
        Ingredient ingredient = new Ingredient(meal);
        List<String> ingredients = ingredient.getIngeredients().stream().filter(Objects::nonNull).filter(s -> (s!=null&&!s.equals(" ")&&!s.isEmpty()&&!s.isBlank())).collect(Collectors.toList());
        Log.i("TAG", "setMealById: ingredients :"+ingredients);
        List<String> measures = ingredient.getMeasures();

        RecyclerView recyclerView = mealView.findViewById(R.id.ingredientRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(mealView.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        Log.i("TAG", "setMealById: ");
        IngredientRA adapter = new IngredientRA(ingredients,measures, mealView.getContext());
        recyclerView.setAdapter(adapter);

    }
}