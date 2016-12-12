package com.fkucuk;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.StringReader;

/**
 * Created by fat on 28.11.2016.
 */
@Path("helper")
public class HelperResource {

    @GET
    @Path("bmi")
    public float calculateBmi(@QueryParam(value = "height")float height,@QueryParam(value = "weight") float weight){
        double heightInMeter = height / 100;
        return (float)(weight / Math.pow(heightInMeter, 2));
    }

    public String getFoodNameFromFoodReport(String foodReport){

        JsonParser jsonParser = Json.createParser(new StringReader(foodReport));
        JsonParser.Event event = jsonParser.next();
        for (int i = 0 ; i< 12; i++){
            event = jsonParser.next();
        }

        String foodName = jsonParser.getString();
        return removeUnnecessaryCharsFromFoodName(foodName);
//        while (jsonParser.hasNext()){
//            JsonParser.Event event = jsonParser.next();
//            switch (event){
//                case START_ARRAY:
//                case END_ARRAY:
//                case START_OBJECT:
//                case END_OBJECT:
//                case VALUE_FALSE:
//                case VALUE_NULL:
//                case VALUE_TRUE:
//                    System.out.println(event.toString());
//                    break;
//                case KEY_NAME:
//                    System.out.print(event.toString() + " " +
//                            jsonParser.getString() + " - ");
//                    break;
//                case VALUE_STRING:
//                case VALUE_NUMBER:
//                    System.out.println(event.toString() + " " +
//                            jsonParser.getString());
//                    break;
//            }
//        }
    }

    public String removeUnnecessaryCharsFromFoodName(String foodName){
        int index = foodName.indexOf(", UPC:");
        foodName =  foodName.substring(0, index);
        return foodName;
    }


}
