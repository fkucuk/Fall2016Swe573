package com.fkucuk;


import com.fkucuk.domain.interfaces.repository.IUserRepository;
import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;
import com.fkucuk.repository.UserRepository;

import javax.ws.rs.*;
import java.util.List;


/**
 * Created by fat on 28.11.2016.
 */
@Path("users") //http:localhost:8080/exercise-services/webapi/users
public class UserResource {

    private UserRepository userRepository = new UserRepository();

    @POST
    public User createUser(User user){
        return userRepository.createUser(user);
    }

    @GET
    @Path("{userId}") //http:localhost:8080/exercise-services/webapi/users/123
    public User getUser(@PathParam("userId") int userId){
        return userRepository.getUser(userId);
    }

    @PUT
    public User getUser(User user){
        return userRepository.updateUser(user);
    }

    @POST
    @Path("{userId}/Meal")
    public void addUserMeal(@PathParam("userId") int userId, Meal meal){
        userRepository.addUserMeal(userId, meal);
    }

    @POST
    @Path("{userId}/Activity")
    public void addUserActivity(@PathParam("userId") int userId, Activity activity){
        userRepository.addUserActivity(userId, activity);
    }

    @GET
    @Path("{userId}/Meals")
    public List<Meal> getUserMeals(@PathParam("userId") int userId,
                                   @QueryParam(value = "startDate") int startDate,
                                   @QueryParam(value = "endDate") int endDate){
        return userRepository.getUserMeals(userId, startDate, endDate);
    }

    @GET
    @Path("{userId}/bmi")
    public float getUserBMI(@PathParam("userId") int userId){
        User user = userRepository.getUser(userId);
        return new HelperResource().calculateBmi(user.getHeight(), user.getWeight());
    }



}
