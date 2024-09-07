package com.example.foodplanner.WeekPlan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.PlanMeal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlanRecyclerAdapter extends RecyclerView.Adapter<PlanRecyclerAdapter.ViewHolder>{
    List<PlanMeal> meals;
    Context context;
    PlanOnClickListener listener;
    List<String> days;

    public PlanRecyclerAdapter(List<PlanMeal> meals , PlanOnClickListener listener, Context context ) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
        days=new ArrayList<>();
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
    }

    public void setMeals(List<PlanMeal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(inflater.inflate(R.layout.plan_card_item,recycler,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dayName.setText(days.get(Integer.valueOf(meals.get(position).getDayId())));
        holder.mealName.setText(meals.get(position).getMealName());
        String imgURl= meals.get(position).getMealImg();
        try {
            Glide.with(context).load(new URL(imgURl))
                    .apply(new RequestOptions().override(200,200)
                            .placeholder(R.drawable.place_holder)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(holder.img);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        /*holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanFragmentDirections.ActionPlanFragmentToMealDetailsFragment action = PlanFragmentDirections
                        .actionHomeFragmentToMealDetailsFragment(
                                meals
                                        .get(holder.getAdapterPosition())
                                        .getMealId()
                        );
                Navigation.findNavController(view).navigate(action);
            }
        });*/
        holder.rmvFromPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanMeal planMeal = meals.get(holder.getAdapterPosition());
                Log.i("PTAG", "BeforeRMV: "+planMeal.toString());
                meals.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                Log.i("PTAG", "onClickRMV: "+planMeal.toString());
                listener.rmvMealFromPlan(planMeal);
            }
        });
//        if(meals.get(holder.getAdapterPosition()).isFavorite()){
//            holder.favButton.setText("is favorite");
//        }else {
//            holder.favButton.setText("favorite");
//        }
//        holder.favButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Meal meal = meals.get(holder.getAdapterPosition());
//                meal.setFavorite(!meal.isFavorite());
//                listener.favoriteBtnClicked(meal);
//            }
//        });
//        holder.planBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.showDaySelectionDialog(meals.get(holder.getAdapterPosition()));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        TextView dayName;
        ImageView img;
        Button rmvFromPlanBtn;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName= itemView.findViewById(R.id.mealName);
            img= itemView.findViewById(R.id.mealImage);
            dayName = itemView.findViewById(R.id.dayName);
            rmvFromPlanBtn = itemView.findViewById(R.id.removeFromPlan);
            card = itemView.findViewById(R.id.card);

        }
    }
}
