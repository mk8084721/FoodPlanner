package com.example.foodplanner.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.model.MyUser;
import com.example.foodplanner.model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FoodPlannerDao {

    @Query("SELECT * FROM FavoriteMeal")
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();
    @Insert(entity = FavoriteMeal.class)
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Update(entity = FavoriteMeal.class)
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Delete(entity = FavoriteMeal.class)
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
    ///Plan
    @Query("SELECT * FROM PlanMeal WHERE userEmail == :email")
    public Flowable<List<PlanMeal>> getPlanMeals(String email);
    @Query("SELECT * FROM PlanMeal WHERE dayId = :dayId AND userEmail == :email")
    public Flowable<PlanMeal> getPlanMealByDayId(String email , String dayId);
    @Query("SELECT * FROM PlanMeal WHERE mealName != '' AND userEmail = :email ")
    public Flowable<List<PlanMeal>> getMealsInPlan(String email);
    @Insert(entity = PlanMeal.class)
    public void insertPlanMeal(PlanMeal... planMeals);
    @Update(entity = PlanMeal.class)
    public void updatePlanMeal(PlanMeal... planMeals);
    @Delete(entity = PlanMeal.class)
    public void deletePlanMeal(PlanMeal... planMeals);

    /// UserPlan
    @Query("SELECT * FROM MyUser WHERE userEmail == :email")
    public Flowable<List<MyUser>> getUserPlanByEmail(String email);
    @Update(entity = MyUser.class)
    public void updateUserPlan(MyUser... users);
    @Delete(entity = MyUser.class)
    public void deleteUserPlan(MyUser... users);
    @Insert(entity = MyUser.class)
    public void insertUserPlan(MyUser...users);

}
