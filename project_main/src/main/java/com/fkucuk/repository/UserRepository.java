package com.fkucuk.repository;

import com.fkucuk.model.*;
import org.sql2o.Connection;

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
