package com.example.foodplanner.profile.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.foodplanner.Login.view.LoginActivity;
import com.example.foodplanner.profile.Repo.profileLocalRepo;
import com.example.foodplanner.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class profilePresenter {
    profileLocalRepo localRepo;
    FirebaseAuth mAuth;
    public profilePresenter(profileLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void rmvLocalData(Activity activity , Context context) {
        localRepo.rmvLocalData(activity, context);
    }

    public void signoutfromGoogle() {
        mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();
    }

}
