package com.example.foodplanner.unUsed;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"id", "user_id"})
public class FavoriteMeals {
    @NonNull
    private Long id;
    @NonNull
    private Long user_id;
    private boolean isFav;
    private String weekPlan;

    @Ignore
    public FavoriteMeals(@NonNull Long id, @NonNull Long user_id, boolean isFav) {
        this.id = id;
        this.user_id = user_id;
        this.isFav = isFav;
    }

    public FavoriteMeals(@NonNull Long id, @NonNull Long user_id, boolean isFav, String weekPlan) {
        this.id = id;
        this.user_id = user_id;
        this.isFav = isFav;
        this.weekPlan = weekPlan;
    }

    @NonNull
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull Long user_id) {
        this.user_id = user_id;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(String weekPlan) {
        this.weekPlan = weekPlan;
    }
}
