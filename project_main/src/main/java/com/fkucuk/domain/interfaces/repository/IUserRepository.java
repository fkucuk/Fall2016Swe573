package com.fkucuk.domain.interfaces.repository;

import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;

import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public interface IUserRepository {
    User createUser(User user);

    User getUser(int userId);

    User updateUser(User user);

    Meal addUserMeal(int userId, Meal meal);

    Activity addUserActivity(int userId, Activity activity);

    List<Meal> getUserMeals(int userId, int startDate, int endDate);

    User getUserByEmail(String email);

    boolean authenticate(String username, String password);
}
