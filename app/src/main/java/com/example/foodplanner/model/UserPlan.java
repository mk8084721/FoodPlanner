package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class UserPlan {
    @NonNull
    private String email;
    @NonNull
    private String userPlan;

    public UserPlan(@NonNull String email, @NonNull String userPlan) {
        this.email = email;
        this.userPlan = userPlan;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUserPlan() {
        return userPlan;
    }

    public void setUserPlan(String userPlan) {
        this.userPlan = userPlan;
    }
}
