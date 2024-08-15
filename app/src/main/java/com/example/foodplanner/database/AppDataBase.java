package com.example.foodplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.FavoriteMeals;
import com.example.foodplanner.model.User;

@Database(entities = {User.class, FavoriteMeals.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract FoodPlannerDao getFoodPlannerDao();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "foodplannerdb")
                    .build();
        }
        return instance;
    }
}
