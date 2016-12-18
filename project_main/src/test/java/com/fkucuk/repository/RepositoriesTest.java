package com.fkucuk.repository;

import com.fkucuk.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

public class RepositoriesTest {

    UserRepository userRepository;
    ActivityRepository activityRepository;
    FoodRepository foodRepository;
    HelperRepository helperRepository;

    public RepositoriesTest(){
        userRepository = new UserRepository();
        foodRepository = new FoodRepository();
        activityRepository = new ActivityRepository();
        helperRepository = new HelperRepository();
    }
    @Test
    public void WHEN_AddValidUser_SHOULDRETURN_PositiveID(){
        User u = new User();
        u.setName("ModelsTest");
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
        f.setFoodId("-123");
        f.setFoodName("TESTFood");


        FoodConsumption fc = new FoodConsumption(f, 150, "gr");

        foodConsumptions.add(fc);
        m.setFoodConsumptions(foodConsumptions);
        List<Meal> meals = new ArrayList<>();
        meals.add(m);



        int result = foodRepository.addUserMeal(1, m);

        Assert.assertTrue(result > 0);
    }


    @Test
    public void WHEN_AddUserActivity_SR_ActivityID(){
        UserActivity ua = new UserActivity();
        Activity a = new Activity();
        a.setDescription("Cycling, mountain bike, bmx");
        a.setActivityId(1);
        a.setCalorieBurnPerHour(695);

        ua.setActivity(a);
        ua.setDuration(120);
        ua.setMoment(new Date());
        ua.setUserId(1);

        int addedActivityId = activityRepository.addUserActivity(ua);
        Assert.assertTrue(addedActivityId > 0);
    }

    @Test
    public void WHEN_SearchActivity_SR_ActivityResults(){
        List<ActivitySearchResult> asr =
                activityRepository.searchActivities("swim");

        Assert.assertFalse(asr.isEmpty());
    }

    @Test
    public void WHEN_SearchActivityWithMinus1_SR_AllActivityResults(){
        List<ActivitySearchResult> asr =
                activityRepository.searchActivities("-1");

        Assert.assertTrue(asr.size() >= 249);
    }

    @Test
    public void WHEN_GetActivityById_SR_RightActivity(){
        Activity expecteda = new Activity();
        expecteda.setCalorieBurnPerHour(654);
        expecteda.setDescription("Cycling, 12-13.9 mph, moderate");
        expecteda.setActivityId(5);

        Activity a =
                activityRepository.getActivity(5);

        Assert.assertEquals(expecteda.getActivityId(), a.getActivityId());
        Assert.assertEquals(expecteda.getCalorieBurnPerHour(), a.getCalorieBurnPerHour(), 0.1);
        Assert.assertEquals(expecteda.getDescription(), a.getDescription());
    }







}
