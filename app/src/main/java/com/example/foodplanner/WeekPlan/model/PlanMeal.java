package com.example.foodplanner.WeekPlan.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlanMeal {
    @PrimaryKey
    private String dayId;
    private String mealId;
    private String mealName;
    private String mealImg;

    public PlanMeal(String dayId, String mealId, String mealName, String mealImg) {
        this.dayId = dayId;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImg = mealImg;
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
