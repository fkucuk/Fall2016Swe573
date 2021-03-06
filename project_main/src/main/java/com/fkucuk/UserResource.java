package com.fkucuk;


import com.fkucuk.model.*;
import com.fkucuk.model.request.FoodLog;
import com.fkucuk.repository.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

@Path("users") //http:localhost:8080/webapi/users
public class UserResource {

    private UserRepository userRepository = new UserRepository();
    private ActivityRepository activityRepository = new ActivityRepository();
    private FoodRepository foodRepository = new FoodRepository();

    @POST
    public User createUser(User user){
        return userRepository.createUser(user);
    }

    @GET
    @Path("{userId}") //http:localhost:8080/webapi/users/123
    public User getUser(@PathParam("userId") int userId){
        return userRepository.getUser(userId);
    }

    @PUT
    public User updateUser(User user){
        return userRepository.updateUser(user);
    }

    @POST
    @Path("{userId}/meals")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addUserMeal(@PathParam("userId") int userId, FoodLog foodLog){
        foodRepository.logUserFood(userId, foodLog);
    }

    @POST
    @Path("{userId}/activity")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addUserActivity(@PathParam("userId") int userId, UserActivity userActivity){
        activityRepository.addUserActivity(userActivity);
    }

    @GET
    @Path("{userId}/meals")
    public Response getUserMeals(@PathParam("userId") int userId,
                                 @QueryParam(value = "startDay") int startDay,
                                 @QueryParam(value = "endDay") @DefaultValue("-1") int endDay){
        if (endDay == -1)
            endDay = startDay;

        UserMeal userMeal = foodRepository.getUserMeal(userId, startDay, endDay);
        if (userMeal == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
           return Response.ok().entity(userMeal).build();
    }

    @GET
    @Path("{userId}/{day}")
    public Response getUserDay(@PathParam("userId") int userId,
                                 @PathParam("day") int day){

        UserDay userDay = new UserDay();

        UserMeal userMeal = foodRepository.getUserMeal(userId, day);
        userDay.setUserMeal(userMeal);

        List<UserActivity> userActivities = activityRepository.getUserActivities(userId, day);
        userDay.setActivityList(userActivities);

        if (userMeal == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(userDay).build();
    }

    @GET
    @Path("{userId}/bmi")
    public BMI getUserBMI(@PathParam("userId") int userId){
        User user = userRepository.getUser(userId);
        return new HelperResource().calculateBmi(user.getHeight(), user.getWeight());
    }



}
