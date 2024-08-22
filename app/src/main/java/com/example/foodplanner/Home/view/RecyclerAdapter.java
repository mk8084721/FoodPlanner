package com.example.foodplanner.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.network.model.Meal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<Meal> meals;
    Context context;
    HomeOnClickListener listener;

    public RecyclerAdapter(List<Meal> meals ,HomeOnClickListener listener, Context context ) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(inflater.inflate(R.layout.card_view,recycler,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        String imgURl= meals.get(position).getStrMealThumb();
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
                HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action = HomeFragmentDirections
                        .actionHomeFragmentToMealDetailsFragment(
                        meals
                                .get(holder.getAdapterPosition())
                                .getIdMeal()
                        );
                Navigation.findNavController(view).navigate(action);
            }
        });
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meal meal = meals.get(holder.getAdapterPosition());
                meal.setFavorite(!meal.isFavorite());
                listener.favoriteBtnClicked(meal);
            }
        });

//        if(isFavoritePage){
//            holder.favButton.setEnabled(true);
//
//        }else {
//            if (products.get(position).isFav()) {
//                holder.favButton.setEnabled(false);
//            } else {
//                holder.favButton.setEnabled(true);
//            }
//        }
//        holder.favButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {listener.updateFavoriteStatus(products.get(holder.getAdapterPosition()),products,isFavoritePage);}
//        });
        //holder.img.setImageResource(products[position].getImages());
//        AppDataBase db = AppDataBase.getInstance(context);
//        ProductDAO productDAO = db.getProductDAO();
//        if(!isFavoritePage) {
//            ProductEntity product = products.get(holder.getAdapterPosition());
//            product.setFav(true);
//
//            new Thread() {
//                @Override
//                public void run() {
//                    productDAO.updateProduct(product);
//                }
//            }.start();
//        }else{
//            ProductEntity product = products.get(holder.getAdapterPosition());
//            product.setFav(false);
//
//            new Thread() {
//                @Override
//                public void run() {
//                    productDAO.updateProduct(product);
//                    products.remove(product);
//                }
//            }.start();
//        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        ImageView img;
        Button favButton;
        Button planBtn;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName= itemView.findViewById(R.id.mealName);
            img= itemView.findViewById(R.id.mealImage);
            favButton = itemView.findViewById(R.id.addToFav);
            planBtn = itemView.findViewById(R.id.addToPlan);
            card = itemView.findViewById(R.id.card);

        }
    }
}
