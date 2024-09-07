package com.example.foodplanner.Login.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.model.MyUser;
import com.example.foodplanner.model.PlanMeal;
import com.example.foodplanner.database.LocalRepo;
import com.example.foodplanner.unUsed.ILogin;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter {
    ILogin view;
    LocalRepo localRepo;
    String email;
    public LoginPresenter( ILogin view , LocalRepo localRepo) {
        this.view = view;
        this.localRepo = localRepo;
    }
   /* public void checkAuth(User user){
        LiveData<User> userLiveData = localRepo.userAuth(user.getEmail(), user.getPassword());
        userLiveData.observe((LifecycleOwner) view, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                boolean islogedin;
                if (user==null) islogedin=false;
                else islogedin=true;
                view.loginStatus(islogedin, user.getId());
            }
        });
    }*/
    public String readSharedPreferance(Activity activity){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        int loginInt = storage.getInt("isLogedin",0);
        if(loginInt==0){
            return null;
        }else{
            email = storage.getString("userEmail", null);
            return email;
        }
    }
    public void writeInSharedPreferance(Activity activity ,int isLogedin , String email){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        editor.putInt("isLogedin",isLogedin);
        editor.putString("userEmail",email);
        Flowable<List<MyUser>> userPlan= localRepo.getUserPlan(email);
        userPlan.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        userPlans -> {
                                            editor.putString("plan",userPlans.get(0).getUserPlan());
                                            editor.commit();
                                        }
                                );

    }
    public void insertEmptyPlanDays(String email){
        Flowable<List<PlanMeal>> flowable = localRepo.getPlanMeals(email);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planMeals -> {
                            if (planMeals.size()<6){
                                for(int i = 0 ; i<7 ; i++){
                                    localRepo.insertPlanMeal(email , new PlanMeal(email , String.valueOf(i),"","",""));
                                }
                            }
                        }
                );

    }

    public void insertEmptyUserPlan(String email) {
        Flowable<List<MyUser>> flowable = localRepo.getUserPlan(email);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myUsers -> {
                            if (myUsers.size()==0){
                                localRepo.insertUserPlan(new MyUser(email,"0000000"));
                            }
                        }
                );
    }

//    public void insertUser() {
//        localRepo.insertUser(new User("m@k.com","mohamed","11"));
//    }
}
