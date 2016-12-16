package com.fkucuk.model;

import javax.json.*;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;


public class Food {

    private String foodId;
    private String foodName;
    private JsonObject foodData;
    private List<Measure> measures;
    private List<Nutrient> nutrients;
    public Food(){}
    public Food(String foodId, String foodName, JsonObject foodData) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodData = foodData;
        this.getMeasures();
        this.getNutrients();
    }

    public List<Measure> getMeasures() {

        if (measures == null){
            List<Measure> m_measures = new ArrayList<>();
            JsonObject jsonObject = (JsonObject) foodData.getJsonObject("report").getJsonObject("food")
                    .getJsonArray("nutrients").get(0);
            // System.out.println(jsonObject.toString());

            JsonArray jsonArray = jsonObject.getJsonArray("measures");

            for (JsonValue jv : jsonArray) {
                JsonObject jv2Json = (JsonObject)jv;
                m_measures.add(new Measure(
                        jv2Json.getString("label")
                        , (float)jv2Json.getJsonNumber("eqv").doubleValue() )
                );
                // System.out.println(((JsonObject)jv).getString("label"));
            }
            measures = m_measures;
        }

        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public List<Nutrient> getNutrients() {

        if (nutrients == null){
            List<Nutrient> m_nutrients = new ArrayList<>();

            String reportType = foodData.getJsonObject("report").getString("type");


            JsonArray jsonArray = foodData.getJsonObject("report").getJsonObject("food")
                    .getJsonArray("nutrients");

            for (JsonValue jv : jsonArray
                    ) {
                JsonObject jo = (JsonObject) jv;

                float value;

                if (reportType.toLowerCase().charAt(0) == 'f'){ value = (float)jo.getJsonNumber("value").doubleValue(); }
                else{ value = Float.parseFloat(jo.getString("value")); }

                if (value == 0 || jo.getString("unit").toLowerCase().equals("kj"))
                    continue;

                m_nutrients.add(new Nutrient(jo.getString("group")
                        , jo.getString("name")
                        , jo.getString("unit")
                        , value)
                );
            }
            nutrients = m_nutrients;
        }

        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    @XmlTransient
    public String getJsonString() {
        return foodData.toString();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public JsonObject getFoodData() {
        return foodData;
    }

    public void setFoodData(JsonObject foodData) {
        this.foodData = foodData;
    }



}
