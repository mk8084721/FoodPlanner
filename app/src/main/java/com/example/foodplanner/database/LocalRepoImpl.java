package com.example.foodplanner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.User;

public class LocalRepoImpl implements LocalRepo{
    private FoodPlannerDao dao;
    private static LocalRepoImpl localSource;

    private LocalRepoImpl(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        dao = dataBase.getFoodPlannerDao();
    }
    public static LocalRepoImpl getInstance(Context context){
        if(localSource==null){
            localSource = new LocalRepoImpl(context);
        }
        return localSource;
    }
    @Override
    public void insertUser(User... user) {
        new Thread(){
            @Override
            public void run() {
                dao.insertUser(user);
            }
        }.start();

    }

    @Override
    public void updateUser(User... user) {
        dao.updateUser(user);
    }

    @Override
    public void deleteUser(User... user) {
        dao.deleteUser(user);
    }

    @Override
    public LiveData<User> getUser(String email) {
        return dao.getUser(email);
    }

    @Override
    public LiveData<User> userAuth(String email, String password) {
        return dao.userAuth(email, password);
    }
}
