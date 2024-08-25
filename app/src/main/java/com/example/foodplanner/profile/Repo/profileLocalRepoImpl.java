package com.example.foodplanner.profile.Repo;

import android.app.Activity;
import android.content.Context;

import com.example.foodplanner.Home.Repo.HomeLocalRepoImpl;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.database.LocalRepoImpl;

public class profileLocalRepoImpl implements profileLocalRepo{
    private LocalRepo localRepo;
    private static profileLocalRepoImpl LocalDataSource = null;

    private profileLocalRepoImpl(Context context) {
        localRepo = LocalRepoImpl.getInstance(context);
    }

    public static profileLocalRepoImpl getInstance(Context context) {
        if(LocalDataSource==null){
            LocalDataSource = new profileLocalRepoImpl(context);
        }
        return LocalDataSource;
    }

    @Override
    public void rmvLocalData(Activity activity , Context context) {
        localRepo.rmvLocalData(activity,context);
    }
}
