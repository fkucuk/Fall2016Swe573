package com.fkucuk.model;

import java.util.List;

/**
 * Created by fat on 17.12.2016.
 */
public class GroupedNutrient {
    private String groupName;
    private List<Nutrient> nutrient;

    public GroupedNutrient() {
    }

    public GroupedNutrient(String groupName, List<Nutrient> nutrient) {
        this.groupName = groupName;
        this.nutrient = nutrient;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Nutrient> getNutrient() {
        return nutrient;
    }

    public void setNutrient(List<Nutrient> nutrient) {
        this.nutrient = nutrient;
    }
}
