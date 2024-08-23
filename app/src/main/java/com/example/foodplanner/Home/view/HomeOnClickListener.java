package com.example.foodplanner.Home.view;

import com.example.foodplanner.network.model.Meal;

import java.util.List;

public interface HomeOnClickListener {
    public void favoriteBtnClicked(Meal meal);
    public void showDaySelectionDialog(Meal meal);
}
