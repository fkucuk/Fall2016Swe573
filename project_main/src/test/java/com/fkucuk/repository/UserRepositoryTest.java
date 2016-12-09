package com.fkucuk.repository;

import com.fkucuk.model.Meal;
import com.fkucuk.model.MealType;
import com.fkucuk.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


        m = userRepository.addUserMeal(1, m);

        Assert.assertTrue(m.getMealId() > 0);
    }

}
