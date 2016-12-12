package com.fkucuk.model.request;

import com.fkucuk.model.Food;

/**
 * Created by fat on 11.12.2016.
 */
public class FoodLog {
    private int userId;
    private int foodId;
    private float quantity;
    private int unitRef;
    private int day;
    private int mealTypeRef;

    public FoodLog(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getUnitRef() {
        return unitRef;
    }

    public void setUnitRef(int unitRef) {
        this.unitRef = unitRef;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMealTypeRef() {
        return mealTypeRef;
    }

    public void setMealTypeRef(int mealTypeRef) {
        this.mealTypeRef = mealTypeRef;
    }
}
