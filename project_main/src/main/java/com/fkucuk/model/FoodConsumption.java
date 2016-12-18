package com.fkucuk.model;

public class FoodConsumption {

    private Food food;
    private float quantity;
    private String unit;

    public FoodConsumption(){}


    public FoodConsumption(Food food, float quantity, String unit){
        this.food = food;
        this.quantity = quantity;
        this.unit = unit;

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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


}
