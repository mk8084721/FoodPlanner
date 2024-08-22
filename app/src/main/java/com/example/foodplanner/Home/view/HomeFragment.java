package com.example.foodplanner.Home.view;

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
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.Home.Repo.HomeRepoImpl;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.presenter.HomePresenter;

import java.util.Arrays;

public class HomeFragment extends Fragment implements IHome,HomeOnClickListener {
    int[] idsTxtView;
    int[] idsRView;
    View homeView;
    HomePresenter presenter;
    int counter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, LocalRepoImpl.getInstance(getContext()),HomeRepoImpl.getInstance());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getAllCategories();
        homeView=view;
        Log.i("TAG", "filterByCategory: counter  "+counter);
        idsTxtView = new int[]{
                R.id.title1,
                R.id.title2,
                R.id.title3,
                R.id.title4,
                R.id.title5,
                R.id.title6,
                R.id.title7,
                R.id.title8,
                R.id.title9,
                R.id.title10,
                R.id.title11,
                R.id.title12,
                R.id.title13,
                R.id.title14,
                R.id.title15
        };
        idsRView = new int[]{
                R.id.rV1,
                R.id.rV2,
                R.id.rV3,
                R.id.rV4,
                R.id.rV5,
                R.id.rV6,
                R.id.rV7,
                R.id.rV8,
                R.id.rV9,
                R.id.rV10,
                R.id.rV11,
                R.id.rV12,
                R.id.rV13,
                R.id.rV14,
                R.id.rV15
        };

    }

    @Override
    public void onPause() {
        super.onPause();
        counter=0;
    }

    @Override
    public void setAllCategories(Category[] categories) {
        for (int i= 0; i< categories.length;i++){
            TextView textView = homeView.findViewById(idsTxtView[i]);
            String categoryName = categories[i].getStrCategory();
            textView.setText(categoryName);
            presenter.filterByCategory(categoryName);
        }
    }

    @Override
    public void filterByCategory(Meal[] meals) {
        Log.i("TAG", "filterByCategory: counter  "+counter);
        RecyclerView recyclerView = homeView.findViewById(idsRView[counter]);
        LinearLayoutManager manager = new LinearLayoutManager(homeView.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(Arrays.asList(meals),this, homeView.getContext());
        recyclerView.setAdapter(adapter);
        counter++;
    }

    @Override
    public void favoriteBtnClicked(Meal meal) {
        if (meal.isFavorite()){
            presenter.insertFavorite(meal);
        }else{
            presenter.deleteFavorite(meal);
        }
    }
}