package com.fkucuk.models;

import com.fkucuk.model.Food;
import com.fkucuk.model.Measure;
import com.fkucuk.model.Nutrient;
import com.fkucuk.repository.FoodRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.OptionalDouble;

/**
 * Created by fat on 13.12.2016.
 */
public class ModelsTest {

    FoodRepository foodRepository = new FoodRepository();


    @Test
    public void CheckNutrientMeasureCalc(){
        Nutrient n = new Nutrient("Proximates", "Energy", "kcal", 125);

        Measure m = new Measure("Tbsp", 16, 1);

        float actual = n.calculateValue(m, 1);
        Assert.assertEquals(actual, 20, 0);

    }

    @Test
    public void CheckNutrientMeasureCalcDifferentQuantity(){
        Nutrient n = new Nutrient("Proximates", "Energy", "kcal", 125);

        Measure m = new Measure("Tbsp", 16, 1);

        float actual = n.calculateValue(m, 2);
        Assert.assertEquals(actual, 40, 0);

    }

    @Test
    public void FoodMeasureTest(){
        String foodId ="01009";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals(28.35, food.getMeasures().get(3).getEqv(), 0.001);
    }

    @Test
    public void FoodMeasure_Label(){
        String foodId ="01009";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals("cubic inch", food.getMeasures().get(4).getLabel());
        Assert.assertEquals("cubic inch", food.getMeasures().get(4).getLabel());
    }

    @Test
    public void FoodNutrients_Count(){
        String foodId ="45121797";

        Food food = foodRepository.getFoodData(foodId);

        for (Nutrient n: food.getNutrients()
             ) {
            System.out.println(n.getGroup() + ": " + n.getName() + " -> " + n.getUnit() + " = " + n.getValue());
        }
        Assert.assertEquals(7, food.getNutrients().size());
    }


    @Test
    public void FoodNutrients_Count_FOR_01009(){
        String foodId ="01009";

        Food food = foodRepository.getFoodData(foodId);

        for (Nutrient n: food.getNutrients()
                ) {
            System.out.println(n.getGroup() + ": " + n.getName() + " -> " + n.getUnit() + " = " + n.getValue());
        }
        Assert.assertEquals(96, food.getNutrients().size());
    }

    @Test
    public void FoodNutrients_CalculateNutritientValue(){
        String foodId ="01009";

        Food food = foodRepository.getFoodData(foodId);
        double d = food.getNutrients().stream().filter(x->x.getName().toLowerCase()
                .equals("energy")).mapToDouble(Nutrient::getValue).findFirst().orElse(-1);
        Assert.assertEquals(d, 404, 0);
    }

    @Test
    public void FoodNutrients_CalculateNutritientValueWithDifferentQuantity(){
        String foodId ="01009";

        Food food = foodRepository.getFoodData(foodId);

        Measure m = food.getMeasures().get(0);

        Assert.assertEquals( 97.74, food.getNutrients().get(0).calculateValue(m, 2), 0.01);
    }

}
