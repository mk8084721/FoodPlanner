package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Favorite.model.FavoriteMeal;
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
    @Insert
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Update
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal);
    @Delete
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal);
}
