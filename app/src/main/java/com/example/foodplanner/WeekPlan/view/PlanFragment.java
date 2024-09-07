package com.example.foodplanner.WeekPlan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.WeekPlan.Repo.LocalPlanRepoImpl;
import com.example.foodplanner.model.PlanMeal;
import com.example.foodplanner.WeekPlan.presenter.PlanPresenter;
import com.example.foodplanner.network.model.Meal;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment implements IPlan,PlanOnClickListener{

    private PlanPresenter presenter;
    private View planView;
    PlanRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlanPresenter(LocalPlanRepoImpl.getInstance(getContext()),this);
        presenter.getMealsInPlan("");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        planView = view;
        RecyclerView recyclerView = planView.findViewById(R.id.planRV);
        LinearLayoutManager manager = new LinearLayoutManager(planView.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new PlanRecyclerAdapter(new ArrayList<>(),this, planView.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setMealsInPlan(List<PlanMeal> planMeals) {
        adapter.setMeals(planMeals);
    }

    @Override
    public void rmvMealFromPlan(PlanMeal planMeal) {

        //remove form ShP
        String plan = presenter.readPlanShp(getActivity());

        StringBuffer planBuffer = new StringBuffer(plan);
        planBuffer.setCharAt(Integer.valueOf(planMeal.getDayId()),'0');
        Meal.setWeekPlan(planBuffer.toString());
        presenter.writePlanShP(getActivity(), planBuffer.toString());

        //remove from DB
        planMeal.setMealName("");
        planMeal.setMealImg("");
        planMeal.setMealId("");
        presenter.updatePlanMeal(planMeal);


    }
}