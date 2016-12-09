package com.fkucuk.model;

/**
 * Created by FATIH.KUCUK on 30.11.2016.
 */
public enum MealType {
    BREAKFAST(1), LUNCH(2), DINNER(3), SNACK(4);

    private final int value;

    MealType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
