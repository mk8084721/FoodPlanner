package com.example.foodplanner.MealDetails.model;

import com.example.foodplanner.network.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private List<String> ingeredients;
    private List<String> measures;


    public Ingredient getIngredientsFromMeal(Meal meal){
        ingeredients.add(meal.getStrIngredient1());
        ingeredients.add(meal.getStrIngredient2());
        ingeredients.add(meal.getStrIngredient3());
        ingeredients.add(meal.getStrIngredient4());
        ingeredients.add(meal.getStrIngredient5());
        ingeredients.add(meal.getStrIngredient6());
        ingeredients.add(meal.getStrIngredient7());
        ingeredients.add(meal.getStrIngredient8());
        ingeredients.add(meal.getStrIngredient9());
        ingeredients.add(meal.getStrIngredient10());
        ingeredients.add(meal.getStrIngredient11());
        ingeredients.add(meal.getStrIngredient12());
        ingeredients.add(meal.getStrIngredient13());
        ingeredients.add(meal.getStrIngredient14());
        ingeredients.add(meal.getStrIngredient15());
        ingeredients.add(meal.getStrIngredient16());
        ingeredients.add(meal.getStrIngredient17());
        ingeredients.add(meal.getStrIngredient18());
        ingeredients.add(meal.getStrIngredient19());
        ingeredients.add(meal.getStrIngredient20());
        measures.add(meal.getStrMeasure1());
        measures.add(meal.getStrMeasure2());
        measures.add(meal.getStrMeasure3());
        measures.add(meal.getStrMeasure4());
        measures.add(meal.getStrMeasure5());
        measures.add(meal.getStrMeasure6());
        measures.add(meal.getStrMeasure7());
        measures.add(meal.getStrMeasure8());
        measures.add(meal.getStrMeasure9());
        measures.add(meal.getStrMeasure10());
        measures.add(meal.getStrMeasure11());
        measures.add(meal.getStrMeasure12());
        measures.add(meal.getStrMeasure13());
        measures.add(meal.getStrMeasure14());
        measures.add(meal.getStrMeasure15());
        measures.add(meal.getStrMeasure16());
        measures.add(meal.getStrMeasure17());
        measures.add(meal.getStrMeasure18());
        measures.add(meal.getStrMeasure19());
        measures.add(meal.getStrMeasure20());
        return this;
    }

    public Ingredient() {
        ingeredients = new ArrayList<>();
    }

    public List<String> getIngeredients() {
        return ingeredients;
    }

    public void setIngeredients(List<String> ingeredients) {
        this.ingeredients = ingeredients;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }
}
