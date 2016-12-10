package com.fkucuk.model;

import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class Meal {
    private int mealId;
    private MealType mealType;
    private int mealDay;
    private List<FoodConsumption> foodConsumptions;


    public Meal(MealType mealType, int mealDay){
        this.mealType = mealType;
        this.mealDay = mealDay;
    }


    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public List<FoodConsumption> getFoodConsumptions() {
        return foodConsumptions;
    }

    public void setFoodConsumptions(List<FoodConsumption> foodConsumptions) {
        this.foodConsumptions = foodConsumptions;
    }

    public int getMealDay() {
        return mealDay;
    }



    public MealType getMealType() {
        return mealType;
    }


}
