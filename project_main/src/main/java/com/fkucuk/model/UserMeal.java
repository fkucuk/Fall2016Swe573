package com.fkucuk.model;

import java.util.List;

/**
 * Created by fat on 09.12.2016.
 */
public class UserMeal {
    private int userId;
    private List<Meal> meals;

    public UserMeal(int userId, List<Meal> meals){
        this.userId = userId;
        this.meals = meals;
    }
}
