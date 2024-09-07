package com.example.foodplanner.Register.Repo;

import com.example.foodplanner.model.PlanMeal;

public interface RegisterRepo {
    void insertPlanMeal(PlanMeal planMeal);
    void insertEmptyUserPlan(String email);
}
