package com.fkucuk;


import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("helper")
public class HelperResource {

    @GET
    @Path("bmi")
    public float calculateBmi(@QueryParam(value = "height")float height,@QueryParam(value = "weight") float weight){
        double heightInMeter = height / 100;
        return (float)(weight / Math.pow(heightInMeter, 2));
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
