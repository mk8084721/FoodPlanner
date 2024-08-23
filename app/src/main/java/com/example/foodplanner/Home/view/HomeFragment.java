package com.example.foodplanner.Home.view;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Home.Repo.HomeLocalRepoImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Home.Repo.HomeRepoImpl;
import com.example.foodplanner.network.model.Category;
import com.example.foodplanner.network.model.Meal;
import com.example.foodplanner.Home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeFragment extends Fragment implements IHome,HomeOnClickListener {
    int[] idsTxtView;
    int[] idsRView;
    View homeView;
    HomePresenter presenter;
    HomeAdapter adapter;
    List<Map<String,Meal[]>> mealsList;
    AtomicInteger selectedDayIndex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, HomeRepoImpl.getInstance() , HomeLocalRepoImpl.getInstance(getContext()));
        mealsList = new ArrayList<>();
        selectedDayIndex = new AtomicInteger(-1);
        Log.i("KTAG", "onCreate: "+presenter.readPlanShP(getActivity()));
        Meal.setWeekPlan(presenter.readPlanShP(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("KTAG", "onCreateView: "+Meal.getWeekPlan());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getAllCategories();
        homeView=view;

        RecyclerView recyclerView = homeView.findViewById(R.id.homeRV);
        LinearLayoutManager manager = new LinearLayoutManager(homeView.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new HomeAdapter(new ArrayList<>(),new HashMap<>(), this , homeView.getContext());
        recyclerView.setAdapter(adapter);

        /*idsTxtView = new int[]{
                R.id.title1,
                R.id.title2,
                R.id.title3,
                R.id.title4,
                R.id.title5,
                R.id.title6,
                R.id.title7,
                R.id.title8,
                R.id.title9,
                R.id.title10,
                R.id.title11,
                R.id.title12,
                R.id.title13,
                R.id.title14,
                R.id.title15
        };
        idsRView = new int[]{
                R.id.rV1,
                R.id.rV2,
                R.id.rV3,
                R.id.rV4,
                R.id.rV5,
                R.id.rV6,
                R.id.rV7,
                R.id.rV8,
                R.id.rV9,
                R.id.rV10,
                R.id.rV11,
                R.id.rV12,
                R.id.rV13,
                R.id.rV14,
                R.id.rV15
        };*/

    }

    @Override
    public void setAllCategories(Category[] categories) {
        Log.i("KTAG", "setAllCategories: "+Meal.getWeekPlan());
        List<String> categoryList = new ArrayList<>();
        for (int i= 0; i< categories.length;i++){
            String categoryName = categories[i].getStrCategory();
            boolean errorCategory = presenter.filterByCategory(categoryName);
            if (errorCategory){

            }else{
                categoryList.add(categoryName);
            }
            Log.i("TAG", "setAllCategories:  category List : \n"+categoryName);
            adapter.setCategoryNames(categoryList);
        }

    }

    @Override
    public void filterByCategory(Map<String,Meal[]> mealMap) {
        adapter.setMeals(mealMap);
//        Log.i("TAG", "filterByCategory: counter  "+counter);
//        RecyclerView recyclerView = homeView.findViewById(idsRView[counter]);
//        LinearLayoutManager manager = new LinearLayoutManager(homeView.getContext(), RecyclerView.HORIZONTAL, false);
//        recyclerView.setLayoutManager(manager);
//        RecyclerAdapter adapter = new RecyclerAdapter(Arrays.asList(meals),this, homeView.getContext());
//        recyclerView.setAdapter(adapter);

    }

    @Override
    public void favoriteBtnClicked(Meal meal) {
        Log.i("KTAG", "favoriteBtnClicked: "+Meal.getWeekPlan());
        if (meal.isFavorite()){
            presenter.insertFavorite(meal);
        }else{
            presenter.deleteFavorite(meal);
        }
    }


    public void addToPlanClicked(Meal meal, int selectedDay) {
        presenter.writePlanShP(getActivity(),Meal.getWeekPlan());
        Log.i("TAG", "addToPlanClicked: \nPlan : "+Meal.getWeekPlan()+"\n selectedDay :"+selectedDay);
    }
    @Override
    public void showDaySelectionDialog(Meal meal) {
        // Generate the list of days for the current week
        ArrayList<String> daysOfWeek = getDaysOfWeek();
        StringBuffer weekPlan =new StringBuffer(presenter.readPlanShP(getActivity()));
        Log.i("KTAG", "showDaySelectionDialog: \n"+ weekPlan);
        StringBuffer emptyDays = new StringBuffer();

        int daysCount =0;
        for (int i= 0 ; i< 7;i++){
            if(weekPlan.charAt(i)=='1'){
                daysOfWeek.remove(daysCount);
            }else {
                emptyDays.append('0');
                daysCount++;
            }
        }
        // Convert to a CharSequence array
        CharSequence[] daysArray = daysOfWeek.toArray(new CharSequence[0]);

        // Create and show the dialog
        new AlertDialog.Builder(getContext())
                .setTitle("Choose a day")
                .setItems(daysArray, (dialog, which) -> {
                    emptyDays.setCharAt(which,'1');
                    int emptyCounter = 0;
                    for(int i=0; i<7;i++){
                        if(weekPlan.charAt(i)=='1'){
                            continue;
                        }else{
                            weekPlan.setCharAt(i,emptyDays.charAt(emptyCounter));
                            if (emptyDays.charAt(emptyCounter)=='1'){
                                selectedDayIndex.set(i);
                            }
                            emptyCounter++;
                        }
                    }
                    Meal.setWeekPlan(String.valueOf(weekPlan));
                    // Handle the day selection
                    String selectedDay = daysOfWeek.get(which);
                    addToPlanClicked(meal, selectedDayIndex.get());
                    Toast.makeText(getContext(), "Selected: "+selectedDay, Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private ArrayList<String> getDaysOfWeek() {
        ArrayList<String> days = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM d", Locale.getDefault());

        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Get the start of the week (assume Sunday as the first day of the week)
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        // Add all days of the week to the list
        for (int i = 0; i < 7; i++) {
            days.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        return days;
    }
}