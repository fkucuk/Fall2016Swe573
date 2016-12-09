package com.fkucuk.model;

/**
 * Created by fat on 09.12.2016.
 */
public class FoodConsumption {

    private Food food;
    private float quantity;
    private FoodUnit unit;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public FoodUnit getUnit() {
        return unit;
    }

    public void setUnit(FoodUnit unit) {
        this.unit = unit;
    }
}
