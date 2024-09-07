package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"userEmail", "dayId"})
public class PlanMeal {
    @NonNull
    private String userEmail;
    @NonNull
    private String dayId;
    private String mealId;
    private String mealName;
    private String mealImg;

    public PlanMeal(@NonNull String userEmail, @NonNull String dayId, String mealId, String mealName, String mealImg) {
        this.userEmail = userEmail;
        this.dayId = dayId;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImg = mealImg;
    }

    @Override
    public String toString() {
        return "PlanMeal{" +
                "userEmail=" + userEmail +
                ", dayId='" + dayId + '\'' +
                ", mealId='" + mealId + '\'' +
                ", mealName='" + mealName + '\'' +
                ", mealImg='" + mealImg + '\'' +
                '}';
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImg() {
        return mealImg;
    }

    public void setMealImg(String mealImg) {
        this.mealImg = mealImg;
    }
}
