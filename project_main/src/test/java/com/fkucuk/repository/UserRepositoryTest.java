package com.fkucuk.repository;

import com.fkucuk.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepositoryTest {

    UserRepository userRepository;
    public UserRepositoryTest(){
        userRepository = new UserRepository();
    }
    @Test
    public void WHEN_AddValidUser_SHOULDRETURN_PositiveID(){
        User u = new User();
        u.setName("Test");
        u.setEmail("test@test.com");

        u.setHeight(100);
        u.setWeight(100);
        u.setPassword("****");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date birthDate = new Date();
        try {
            birthDate = sdf.parse("19820523");
        }catch (ParseException e){
            System.out.println("Unparseable using " + sdf);
        }

        u.setBirthDate(birthDate);

        u = userRepository.createUser(u);

        Assert.assertTrue(u.getUserId() > 0);
    }

    @Test
    public void WHEN_AddMealToUser_SR_ValidMeal(){
        Meal m = new Meal(MealType.BREAKFAST, 20161209);
        List<FoodConsumption> foodConsumptions = new ArrayList<>();

        Food f = new Food();
        f.setId(-123);
        f.setName("TESTFood");


        FoodUnit fu = new FoodUnit();
        fu.setName("gr");



        FoodConsumption fc = new FoodConsumption(f, 150, fu);

        foodConsumptions.add(fc);
        m.setFoodConsumptions(foodConsumptions);
        List<Meal> meals = new ArrayList<>();
        meals.add(m);



        int result = userRepository.addUserMeal(1, m);

        Assert.assertTrue(result > 0);
    }

    @Test
    public void WHEN_GetUserMeal_SR_NotNull(){
        UserMeal userMeal = userRepository.getUserMeal(1, 20161209);
        Assert.assertTrue(userMeal != null);
    }
}
