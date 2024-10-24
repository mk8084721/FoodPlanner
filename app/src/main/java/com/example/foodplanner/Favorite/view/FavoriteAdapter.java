package com.example.foodplanner.Favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.model.FavoriteMeal;
import com.example.foodplanner.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    List<FavoriteMeal> meals;
    Context context;
    FavoriteOnClickListener listener;
    public FavoriteAdapter(List<FavoriteMeal> meals ,FavoriteOnClickListener listener ,  Context context ) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(inflater.inflate(R.layout.favorite_card_view,recycler,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean internetConnection = false;
                if(internetConnection){
                    /*HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action = HomeFragmentDirections
                            .actionHomeFragmentToMealDetailsFragment(
                                    meals
                                            .get(holder.getAdapterPosition())
                                            .getIdMeal()
                            );
                    Navigation.findNavController(view).navigate(action);*/
                }else {

                }

            }
        });
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeMealFromFavorite(meals,meals.get(holder.getAdapterPosition()));
            }
        });

    }
    public void setMealsList(List<FavoriteMeal> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        ImageView img;
        ImageButton favButton;
        Button planBtn;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName= itemView.findViewById(R.id.mealName);
            img= itemView.findViewById(R.id.mealImage);
            favButton = itemView.findViewById(R.id.favBtn);
            planBtn = itemView.findViewById(R.id.removeFromPlan);
            card = itemView.findViewById(R.id.card);

        }
    }

}
