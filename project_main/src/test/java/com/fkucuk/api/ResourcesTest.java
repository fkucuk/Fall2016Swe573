package com.fkucuk.api;

import com.fkucuk.HelperResource;
import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * Created by fat on 09.12.2016.
 */
public class ResourcesTest {
    HelperResource hr;
    public ResourcesTest(){
        hr = new HelperResource();
    }

    @Test
    public void WHEN_CalculateBMI_SHOULD_BeCorrect(){
        float result  = hr.calculateBmi(187, 95);
        Assert.assertEquals(27.16692, result, 0.05);
    }

    @Test
    public void WHEN_CallHelperGetFoodName_SR_ProperFoodName(){

        //region long String
        String jsonStr = "{\n" +
                "    \"report\": {\n" +
                "        \"sr\": \"September, 2016\",\n" +
                "        \"type\": \"Basic\",\n" +
                "        \"food\": {\n" +
                "            \"ndbno\": \"45078606\",\n" +
                "            \"name\": \"AARDVARK HABENERO HOT SAUCE, UPC: 853393000030\",\n" +
                "            \"ds\": \"Branded Food Products\",\n" +
                "            \"nutrients\": [\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"208\",\n" +
                "                    \"name\": \"Energy\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"kcal\",\n" +
                "                    \"value\": \"0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"tsp\",\n" +
                "                            \"eqv\": 5.0,\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"203\",\n" +
                "                    \"name\": \"Protein\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.00\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"tsp\",\n" +
                "                            \"eqv\": 5.0,\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.00\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"204\",\n" +
                "                    \"name\": \"Total lipid (fat)\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.00\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"tsp\",\n" +
                "                            \"eqv\": 5.0,\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.00\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"205\",\n" +
                "                    \"name\": \"Carbohydrate, by difference\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.00\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"tsp\",\n" +
                "                            \"eqv\": 5.0,\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.00\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"307\",\n" +
                "                    \"name\": \"Sodium, Na\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"700\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"tsp\",\n" +
                "                            \"eqv\": 5.0,\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"35\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"footnotes\": [\n" +
                "            \n" +
                "        ]\n" +
                "    }\n" +
                "}";
        //endregion

        JsonReader jsonReader = Json.createReader(new StringReader(jsonStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        String actualFoodName = hr.getFoodNameFromFoodReport(object);

        Assert.assertEquals("AARDVARK HABENERO HOT SAUCE", actualFoodName);
    }


}
