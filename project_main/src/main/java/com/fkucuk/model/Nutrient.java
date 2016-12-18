package com.fkucuk.model;

/**
 * Created by fat on 13.12.2016.
 */
public class Nutrient {
    private String unit;
    private float value;
    private String name;
    private String group;

    public Nutrient(){}

    public Nutrient(String group, String name, String unit, float value) {
        this.group = group;
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float calculateValue(Measure measure, int quantity){
        return (quantity / measure.getQty()) * (measure.getEqv() * this.getValue() / 100);
    }
}
