package com.example.foodplanner.network.model;

public class Meals {
    private Meal[] meals;

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i< meals.length;i++){
            stringBuffer.append(meals[i].toString());
        }
        return "Products{" +
                "products=" +stringBuffer.toString()+
                '}';
    }
}
