package com.example.foodplanner.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.Login.view.LoginActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.WeekPlan.Repo.LocalPlanRepoImpl;
import com.example.foodplanner.profile.Repo.profileLocalRepoImpl;
import com.example.foodplanner.profile.presenter.profilePresenter;
import com.example.foodplanner.view.MainActivity;


public class profileFragment extends Fragment {
    profilePresenter presenter;
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new profilePresenter(profileLocalRepoImpl.getInstance(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.rmvLocalData(getActivity(),getContext());
                presenter.signoutfromGoogle();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
    }
}