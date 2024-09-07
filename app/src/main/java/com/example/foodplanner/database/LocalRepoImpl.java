package com.example.foodplanner.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.model.MyUser;
import com.example.foodplanner.model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalRepoImpl implements LocalRepo{
    private FoodPlannerDao dao;
    private static LocalRepoImpl localSource;
    private static String EMAIL;
    private LocalRepoImpl(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        dao = dataBase.getFoodPlannerDao();

    }
    public static LocalRepoImpl getInstance(Context context){
        if(localSource==null){
            localSource = new LocalRepoImpl(context);
            EMAIL = context.getSharedPreferences("STORAGE", Context.MODE_PRIVATE).getString("userEmail","");
        }
        return localSource;
    }

/*    public void insertUser(User... user) {
        new Thread(){
            @Override
            public void run() {
                dao.insertUser(user);
            }
        }.start();

    }


    public void updateUser(User... user) {
        dao.updateUser(user);
    }


    public void deleteUser(User... user) {
        dao.deleteUser(user);
    }


    public LiveData<User> getUser(String email) {
        return dao.getUser(email);
    }


    public LiveData<User> userAuth(String email, String password) {
        return dao.userAuth(email, password);
    }*/

    @Override
    public void insertFavoriteMeal(FavoriteMeal... favoriteMeal) {
        new Thread(){
            @Override
            public void run() {
                dao.insertFavoriteMeal(favoriteMeal);
            }
        }.start();

    }

    @Override
    public void updateFavoriteMeal(FavoriteMeal... favoriteMeal) {
        dao.updateFavoriteMeal(favoriteMeal);
    }

    @Override
    public void deleteFavoriteMeal(FavoriteMeal... favoriteMeal) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteFavoriteMeal(favoriteMeal);
            }
        }.start();

    }

    ///////////plan

    @Override
    public Flowable<List<FavoriteMeal>> getFavoriteMeals() {
        return dao.getFavoriteMeals();
    }

    @Override
    public Flowable<List<PlanMeal>> getPlanMeals(String email) {
        return dao.getPlanMeals(email);
    }

    @Override
    public Flowable<PlanMeal> getPlanMealByDayId(String dayId) {
        return dao.getPlanMealByDayId(EMAIL , dayId);
    }

    @Override
    public void insertPlanMeal(String email , PlanMeal... planMeals) {
        new Thread(){
            @Override
            public void run() {
                dao.insertPlanMeal(planMeals);
            }
        }.start();
    }

    @Override
    public void updatePlanMeal(PlanMeal... planMeals) {
        new Thread(){
            @Override
            public void run() {
                dao.updatePlanMeal(planMeals);
            }
        }.start();

    }

    @Override
    public void deletePlanMeal(PlanMeal... planMeals) {
        new Thread(){
            @Override
            public void run() {
                dao.deletePlanMeal(planMeals);

            }
        }.start();
    }

    ///////////ShP

    @Override
    public String readPlanShP(Activity activity){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        return storage.getString("plan","0000000");
    }
    @Override
    public String readEmailShP(Context context){
        SharedPreferences storage = context.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        return storage.getString("userEmail","");
    }

    @Override
    public Flowable<List<PlanMeal>> getMealsInPlan(String emptyString) {
        return dao.getMealsInPlan(emptyString);
    }

    @Override
    public void rmvLocalData(Activity activity ,Context context) {
        String plan = readPlanShP(activity);
        String email = readEmailShP(activity);
        Log.i("Profile", "rmvLocalData: email : "+email + "\nplan : "+plan);
        new Thread(){
            @Override
            public void run() {
                dao.updateUserPlan(new MyUser(email,plan));
            }
        }.start();
        clearShP(activity,context);
    }

    @Override
    public void insertUserPlan(MyUser userPlan) {
        new Thread(){
            @Override
            public void run() {
                dao.insertUserPlan(userPlan);
            }
        }.start();
    }

    @Override
    public Flowable<List<MyUser>> getUserPlan(String email) {
        return dao.getUserPlanByEmail(email);
    }

    private void clearShP(Activity activity ,Context context) {
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void writePlanShP(Activity activity , String plan){
        SharedPreferences storage = activity.getSharedPreferences("STORAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        editor.putString("plan",plan);
        editor.commit();
    }
}
