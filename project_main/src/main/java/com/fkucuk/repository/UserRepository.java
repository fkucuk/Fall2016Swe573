package com.fkucuk.repository;

import com.fkucuk.domain.interfaces.repository.IUserRepository;
import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class UserRepository {

    public User createUser(User user) {
        //TODO: implement
        user.setUserId(123);
        return user;
    }

    public User getUser(int userId) {
        User u = new User();
        u.setUserId(userId);
        u.setHeight(190);
        u.setWeight(200);
        u.setName("Fatih");
        u.setBirthDate(Date.from(Instant.now()));
        u.setEmail("fkucuk@gmail.com");
        u.setSurname("Küçük");
        return u;
    }

    public User updateUser(User user) {
        return user;
    }

    public Meal addUserMeal(int userId, Meal meal) {
        return null;
    }


    public Activity addUserActivity(int userId, Activity activity) {
        return null;
    }


    public List<Meal> getUserMeals(int userId, int startDate, int endDate) {
        return null;
    }


    public User getUserByEmail(String email) {
        int userId = getUserIdByEmail(email);
        return this.getUser(userId);
    }

    public int getUserIdByEmail(String email){
        //TODO:
        return 123;
    }

    public boolean authenticate(String username, String password) {
        return false;
    }
}
