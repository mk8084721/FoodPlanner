package com.example.foodplanner.network.model;

public class Meals {
    private Meal[] meals;
    private Category[] categories;

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
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
