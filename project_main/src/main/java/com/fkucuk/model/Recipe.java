package com.fkucuk.model;

import java.util.List;

/**
 * Created by FATIH.KUCUK on 30.11.2016.
 */
public class Recipe {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    private String name;
    private List<Food> foods;

}

