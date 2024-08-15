package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.model.User;

public interface LocalRepo {
    public void insertUser(User... user);
    public void updateUser(User... user);
    public void deleteUser(User... user);
    public LiveData<User> getUser(String email);
    public LiveData<User> userAuth(String email , String password);
}
