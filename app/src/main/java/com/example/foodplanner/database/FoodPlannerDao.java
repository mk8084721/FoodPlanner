package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FoodPlannerDao {
    /*@Insert(entity = User.class)
    public void insertUser(User... user);
    @Update(entity = User.class)
    public void updateUser(User... user);
    @Delete(entity = User.class)
    public void deleteUser(User... user);
    @Query("SELECT * FROM FavoriteMeal WHERE email=:email")
    public LiveData<User> getUser(String email);
    @Query("SELECT * FROM User WHERE email=:email AND password = :password")
    LiveData<User> userAuth(String email, String password);*/
    @Query("SELECT * FROM FavoriteMeal")
    public Flowable<List<FavoriteMeal>> getFavoriteMeals();
    @Insert(entity = FavoriteMeal.class)
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Update(entity = FavoriteMeal.class)
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Delete(entity = FavoriteMeal.class)
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
    ///Plan
    @Query("SELECT * FROM PlanMeal")
    public Flowable<List<PlanMeal>> getPlanMeals();
    @Query("SELECT * FROM PlanMeal WHERE dayId = :dayId")
    public Flowable<PlanMeal> getPlanMealByDayId(String dayId);
    @Query("SELECT * FROM PlanMeal WHERE mealName != :emptyString")
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString);
    @Query("UPDATE PlanMeal SET mealId = :mealId , mealName= :mealName, mealImg= :mealImg WHERE dayId = :dayId;")
    public void updatePlanMealByDayId(String mealId , String mealImg , String mealName , String dayId);
    @Insert(entity = PlanMeal.class)
    public void insertPlanMeal(PlanMeal... planMeals);
    @Update(entity = PlanMeal.class)
    public void updatePlanMeal(PlanMeal... planMeals);
    @Delete(entity = PlanMeal.class)
    public void deletePlanMeal(PlanMeal... planMeals);

}
