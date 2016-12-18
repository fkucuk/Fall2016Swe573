package com.fkucuk.domain.interfaces.repository;

import com.fkucuk.model.Food;
import com.fkucuk.model.Meal;
import com.fkucuk.model.UserMeal;
import com.fkucuk.model.request.FoodLog;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 * Created by fat on 18.12.2016.
 */
public interface IFoodRepository {
    Response searchFood(String keyword);

    UserMeal getUserMeal(int userId, int mealDay);

    UserMeal getUserMeal(int userId, int startDay, int endDay);

    int addUserMeal(int userId, Meal meal);

    void logUserFood(int userId, FoodLog foodLog);

    int createUserMeal(int userId, int mealTypeId, int day);

    JsonObject getFoodReportFromUSDA(String foodId);

    Food getFoodData(String foodId);
}
