package com.fkucuk.model;

import java.util.List;

public class UserMeal {
    private int userId;
    private List<Meal> meals;

    public UserMeal(){}

    public UserMeal(int userId, List<Meal> meals){
        this.userId = userId;
        this.meals = meals;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
