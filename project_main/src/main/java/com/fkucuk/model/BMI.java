package com.fkucuk.model;

/**
 * Created by fat on 19.12.2016.
 */
public class BMI {
    private float value;
    private String desc;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDesc() {
        String description;
        if(this.getValue() < 18.5)
            description = "Underweight";
        else if(this.getValue() < 24.9)
            description = "Normal Weight";
        else if (this.getValue() < 29.9)
            description = "Overweight";
        else
            description = "Obesity";

        return description;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
