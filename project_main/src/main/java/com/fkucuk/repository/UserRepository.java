package com.fkucuk.repository;

import com.fkucuk.domain.interfaces.repository.IUserRepository;
import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;

import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class UserRepository implements IUserRepository {



    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void addUserMeal(int userId, Meal meal) {

    }

    @Override
    public void addUserActivity(int userId, Activity activity) {

    }

    @Override
    public List<Meal> getUserMeals(int userId, int startDate, int endDate) {
        return null;
    }
}
