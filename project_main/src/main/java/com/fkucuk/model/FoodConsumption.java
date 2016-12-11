package com.fkucuk.model;

public class FoodConsumption {

    private Food food;
    private float quantity;
    private FoodUnit unit;

    public FoodConsumption(){}
    public FoodConsumption(Food food, float quantity, FoodUnit unit){
        this.food = food;
        this.quantity = quantity;
        this.unit = unit;
    }

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
