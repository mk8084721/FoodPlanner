package com.example.foodplanner.Favorite.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteMeal {
    @PrimaryKey
    @NonNull
    private String mealId;
    private String mealImg;
    private String mealName;

    public FavoriteMeal(String mealId, String mealImg, String mealName) {
        this.mealId = mealId;
        this.mealImg = mealImg;
        this.mealName = mealName;
    }


    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealImg() {
        return mealImg;
    }

    public void setMealImg(String mealImg) {
        this.mealImg = mealImg;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
