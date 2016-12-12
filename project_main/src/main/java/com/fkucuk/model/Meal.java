package com.fkucuk.model;

import java.util.List;

public class Meal {
    private int mealId;
    private MealType mealType;

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void setMealDay(int mealDay) {
        this.mealDay = mealDay;
    }

    private int mealDay;
    private List<FoodConsumption> foodConsumptions;

    public Meal(){}
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
