package com.fkucuk;


import com.fkucuk.model.BMI;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


@Path("helper")
public class HelperResource {

    @GET
    @Path("bmi")
    public BMI calculateBmi(@QueryParam(value = "height")float height, @QueryParam(value = "weight") float weight){
        BMI bmi = new BMI();
        double heightInMeter = height / 100;
        double bmiValue = weight / Math.pow(heightInMeter, 2);
        bmiValue = Math.round(bmiValue * 100.0) / 100.0;
        bmi.setValue((float)bmiValue);
        return bmi;
    }

    public String getFoodNameFromFoodReport(JsonObject foodReport){

        String foodName = foodReport.getJsonObject("report").getJsonObject("food").getString("name");
        return removeUnnecessaryCharsFromFoodName(foodName);
    }

    public String removeUnnecessaryCharsFromFoodName(String foodName){
        int index = foodName.indexOf(", UPC:");
        if (index != -1)
            foodName =  foodName.substring(0, index);
        return foodName;
    }


}
