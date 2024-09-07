package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"userEmail", "mealId"})
public class FavoriteMeal {
    @NonNull
    private String userEmail;
    @NonNull
    private String mealId;
    private String mealImg;
    private String mealName;

    public FavoriteMeal(@NonNull String userEmail, @NonNull String mealId, String mealImg, String mealName) {
        this.userEmail = userEmail;
        this.mealId = mealId;
        this.mealImg = mealImg;
        this.mealName = mealName;
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
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
