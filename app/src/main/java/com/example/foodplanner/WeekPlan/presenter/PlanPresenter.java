package com.example.foodplanner.WeekPlan.presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.example.foodplanner.Home.Repo.HomeLocalRepo;
import com.example.foodplanner.Home.view.IHome;
import com.example.foodplanner.WeekPlan.Repo.LocalPlanRepo;
import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.WeekPlan.view.IPlan;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter {
    private LocalPlanRepo localRepo;
    private IPlan view;

    public PlanPresenter(LocalPlanRepo localRepo, IPlan view) {
        this.localRepo = localRepo;
        this.view = view;
    }
    public void getMealsInPlan(String emptyString){
         Flowable<List<PlanMeal>> flowable = localRepo.getMealsInPlan(emptyString);
         flowable.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                         planMeals -> {
                             view.setMealsInPlan(planMeals);
                         }
                 );
    }

    public String readPlanShp(Activity activity) {
        return localRepo.readPlanShp(activity);
    }

    public void writePlanShP(Activity activity, String plan) {
        localRepo.writePlanShP(activity, plan);
    }

    public void updatePlanMeal(PlanMeal planMeal) {
        localRepo.updatePlanMeal(planMeal);
    }
}
