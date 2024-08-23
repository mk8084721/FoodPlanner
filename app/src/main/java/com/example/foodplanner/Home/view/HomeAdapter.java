package com.example.foodplanner.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.network.model.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    List<String> categoryNames;
    List<RecyclerView> recyclerViews;
    List<Meal[]>meals;
    Map<String , Meal[]> mealMap;
    Context context;
    List<RecyclerAdapter> adapterList;
    HomeOnClickListener homeListener;



    public HomeAdapter(List<String> categoryNames, Map<String , Meal[]> mealMap,HomeOnClickListener homeListener , Context context) {
        this.categoryNames = categoryNames;
        this.mealMap = mealMap;
        this.homeListener = homeListener;
        this.context = context;
        adapterList=new ArrayList<>();
    }

    public void setMeals(Map<String , Meal[]> mealMap) {
        this.mealMap = mealMap;

        notifyDataSetChanged();
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(inflater.inflate(R.layout.category_recycler_layout,recycler,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryNames.get(position));
        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.mealRV.setLayoutManager(manager);
        if (mealMap.size()>11){
            RecyclerAdapter adapter = new RecyclerAdapter(Arrays.asList(mealMap.get(categoryNames.get(holder.getAdapterPosition()))), homeListener, context);
            holder.mealRV.setAdapter(adapter);
        }
//        if (adapterList.size()<holder.getAdapterPosition()){
//            adapterList.add(holder.getAdapterPosition(),new RecyclerAdapter(new ArrayList<>(), homeListener, context));
//            holder.mealRV.setAdapter(adapterList.get(holder.getAdapterPosition()));
//        }
//        adapterList.get(holder.getAdapterPosition()).setMeals(Arrays.asList(meals.get(holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return mealMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        RecyclerView mealRV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName= itemView.findViewById(R.id.title);
            mealRV= itemView.findViewById(R.id.rV);


        }
    }
}
