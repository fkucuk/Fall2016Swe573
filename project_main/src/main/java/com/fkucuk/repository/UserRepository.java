package com.fkucuk.repository;

import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;
import org.sql2o.Connection;

import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class UserRepository {

    public User createUser(User user) {
        String sql = "INSERT INTO USER (email, name, password, isActive, weight, height)" +
                "VALUES (:email,:name,:password, :isActive,:weight,:height)";

        try (Connection con = DbHelper.getSql2o().open()){

            Long insertedId = con.createQuery(sql)
                    .addParameter("email", user.getEmail())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("isActive", user.isActive())
                    .addParameter("weight", user.getWeight())
                    .addParameter("height", user.getHeight())
                    .executeUpdate()
                    .getKey(Long.class);
            user.setUserId(insertedId);
        }

        return user;
    }


    public User getUser(int userId) {
        String sql = "SELECT * FROM User WHERE userId = :userId";

        try(Connection con = DbHelper.getSql2o().open()) {
            return con.createQuery(sql).addParameter("userId", userId).executeAndFetchFirst(User.class);
        }
    }

    public User updateUser(User user) {

        String sql = "UPDATE User SET name = :name, password = :password, isActive = :isActive, weight = :weight, height = :height " +
                "WHERE userId = :userId";
//        try
//        {Class.forName("com.mysql.jdbc.Driver");} catch(ClassNotFoundException e){
//        }


        try(Connection con = DbHelper.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("isActive", user.isActive())
                    .addParameter("weight", user.getWeight())
                    .addParameter("height", user.getHeight())
                    .addParameter("userId", user.getUserId())
                    .executeUpdate();
        }
        return user;
    }

    public int createUserMeal(int userId, int mealTypeId, int day) {
        String sql = "INSERT INTO UserMeal (UserId, MealTypeId, Day)" +
                "VALUES (:userId, :mealTypeId, :day)";
        int result;
        try(Connection con = DbHelper.getSql2o().open()){
            result = con.createQuery(sql)
                    .addParameter(":userId", userId)
                    .addParameter(":mealTypeId", mealTypeId)
                    .addParameter(":day", day)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
        return result;

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

        String sql = "SELECT userId FROM USER " +
                "WHERE email = :email";
        try (Connection con = DbHelper.getSql2o().open()){

            return con.createQuery(sql)
                    .addParameter("email",email)
                    .executeScalar(Integer.class);
        }
    }

    public boolean authenticate(String username, String password) {
        return false;
    }
}
