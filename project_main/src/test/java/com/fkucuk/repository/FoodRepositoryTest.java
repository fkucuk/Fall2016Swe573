package com.fkucuk.repository;

import com.fkucuk.model.Food;
import com.fkucuk.model.UserMeal;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;

/**
 * Created by fat on 13.12.2016.
 */
public class FoodRepositoryTest {

    FoodRepository foodRepository = new FoodRepository();

    @Test
    public void WHEN_GetFood_SR_Food(){

        String foodId ="45121797";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals(food.getFoodId(), foodId);
    }

    @Test
    public void WHEN_GetFood_SR_FoodJSON(){

        String foodId ="09001";

        Food food = foodRepository.getFoodData(foodId);
        String actualFoodId = food.getFoodData().getJsonObject("report").getJsonObject("food").getString("ndbno");
        Assert.assertEquals(actualFoodId, foodId);
    }

    @Test
    public void WHEN_NonExistentGetFood_SR_FoodJSON(){

        String foodId ="09001";

        Food food = foodRepository.getFoodData(foodId);
        String actualFoodId = food.getFoodData().getJsonObject("report").getJsonObject("food").getString("ndbno");
        Assert.assertEquals(actualFoodId, foodId);
    }

    @Test
    public void WHEN_GetFoodReportFromApi_SR_ReturnRelatedReport(){
        JsonObject foodReport = foodRepository.getFoodReportFromUSDA("01009");
        Assert.assertNotNull(foodReport);
    }

    @Test
    public void WHEN_GetFoodReportFromApiForNonExistentFood_SR_ReturnNull(){
        JsonObject foodReport = foodRepository.getFoodReportFromUSDA("-12");
        Assert.assertNull(foodReport);
    }

    @Test
    public void WHEN_GetNonExistentFood_ShouldBeSaved(){

        String foodId ="45130172";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals(food.getFoodId(), foodId);
    }

    @Test
    public void WHEN_GetNonUPCFood_ShouldBeOK(){

        String foodId ="03173";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals(food.getFoodId(), foodId);
    }

    @Test
    public void WHEN_GetFood_SR_CorrectFormattedFoodName(){

        String foodId ="45121797";

        Food food = foodRepository.getFoodData(foodId);

        Assert.assertEquals( "A. 1., CHICAGO STEAKHOUSE, MARINADE", food.getFoodName());
    }

    @Test
    public void WHEN_GetUserMeal_SR_NotNull(){
        UserMeal userMeal = foodRepository.getUserMeal(1, 20161209);
        System.out.println(userMeal.toString());
        Assert.assertNotNull(userMeal );
    }

}
