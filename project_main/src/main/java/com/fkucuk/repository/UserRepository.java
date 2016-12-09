package com.fkucuk.repository;

import com.fkucuk.model.*;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class UserRepository {

    public User createUser(User user) {
        String sql = "INSERT INTO User (Email, Name, Password, IsActive, Weight, Height)" +
                "VALUES (:email, :name, :password, :isActive, :weight, :height)";

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
        String sql = "SELECT * FROM User WHERE UserId = :userId";

        try(Connection con = DbHelper.getSql2o().open()) {
            return con.createQuery(sql).addParameter("userId", userId).executeAndFetchFirst(User.class);
        }
    }

    public User updateUser(User user) {

        String sql = "UPDATE User SET Name = :name, Password = :password, IsActive = :isActive "+
                ", Weight = :weight, Height = :height " +
                "WHERE UserId = :userId";

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
        String sql = "INSERT INTO UserMeal (UserId, MealTypeId, MealDay) " +
                "VALUES (:userId, :mealTypeId, :mealDay)";
        int result;
        try(Connection con = DbHelper.getSql2o().open()){
            result = con.createQuery(sql)
                    .addParameter(":userId", userId)
                    .addParameter(":mealTypeId", mealTypeId)
                    .addParameter(":mealDay", day)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
        return result;

    }

    public Meal addUserMeal(int userId, Meal meal) {

        final String sqlExists = "SELECT UserMealId FROM UserMeal "+
                "WHERE MealTypeId = :mealTypeId " +
                " AND MealDay = :mealDay AND UserId = :userId";

        final String sqlInsert = "INSERT INTO UserMeal (UserId, MealTypeId, MealDay) " +
                "VALUES (:userId, :mealTypeId, :mealDay)";

        final String sqlFoods = "INSERT INTO MealFood (UserMealId, FoodId, Unit, Quantity) " +
                "VALUES (:userMealId, :foodId, :unit, :quantity)";

         try(Connection conn = DbHelper.getSql2o().beginTransaction()){

             Integer userMealId =  conn.createQuery(sqlExists)
                     .addParameter("userId", userId)
                     .addParameter("mealTypeId", meal.getMealType())
                     .addParameter("mealDay", meal.getMealDay())
                     .executeScalar(Integer.class);

             if (userMealId == null){
                 userMealId = conn.createQuery(sqlInsert)
                         .addParameter("userId", userId)
                         .addParameter("mealTypeId", (int)meal.getMealType().getValue())
                         .addParameter("mealDay", meal.getMealDay())
                         .executeUpdate()
                         .getKey(Integer.class);
             }

             Query foodQuery = conn.createQuery(sqlFoods);

             for (FoodConsumption f : meal.getFoodConsumptions()
                  ) {
                 foodQuery.addParameter("userMealId", userMealId)
                         .addParameter("foodId", f.getFood().getId())
                         .addParameter("unit", f.getUnit().getName())
                         .addParameter("quantity", f.getQuantity())
                         .addToBatch();
             }

             foodQuery.executeBatch();
             conn.commit();
         }

         return getUserMeal(userId, meal.getMealDay(), meal.getMealType());
    }

    public Meal getUserMeal(int userId, int day, MealType mealType){
//        final String sql = "SELECT * FROM UserMeal um " +
//                "JOIN MealFood mf ON um.mealId = mf.mealId" +
//                "WHERE um.UserId = :userId"
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

        String sql = "SELECT userId FROM User " +
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
