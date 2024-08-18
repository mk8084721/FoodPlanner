package com.example.foodplanner.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.network.NetworkCallback;
import com.example.foodplanner.network.RemoteRebo;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.view.IHome;

import java.util.List;

public class HomePresenter implements NetworkCallback {
    RemoteRebo remoteRebo;
    IHome view;
    public HomePresenter(IHome view , RemoteRebo remoteDataSource) {
        this.remoteRebo = remoteDataSource;
        this.view = view;
    }
    public void getAllProducts(){
        remoteRebo.makeNetworkCall(this);

    }

    @Override
    public void onResponse(Meal[] Meals) {

    }

    @Override
    public void onFailure(String errorMsg) {

    }
}
