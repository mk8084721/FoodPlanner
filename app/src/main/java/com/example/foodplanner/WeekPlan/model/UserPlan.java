package com.example.foodplanner.WeekPlan.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserPlan {
    @PrimaryKey
    @NonNull
    private String userEmail;
    private String plan;

    public UserPlan(@NonNull String userEmail, String plan) {
        this.userEmail = userEmail;
        this.plan = plan;
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
