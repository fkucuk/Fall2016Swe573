package com.fkucuk.repository;

import com.fkucuk.model.UserActivity;
import org.sql2o.Connection;



public class ActivityRepository {

    public int addUserActivity(UserActivity userActivity) {

        final String sqlInsert = "INSERT INTO UserActivity (UserId, ActivityId, Duration, Moment) " +
                "VALUES (:userId, :activityId, :duration, :moment)";

        try(Connection conn = DbHelper.getSql2o().open()) {


                return conn.createQuery(sqlInsert)
                        .addParameter("userId", userActivity.getUserId())
                        .addParameter("activityId", userActivity.getActivity().getId())
                        .addParameter("duration", userActivity.getDuration())
                        .addParameter("moment", userActivity.getMoment())
                        .executeUpdate()
                        .getKey(Integer.class);



        }
    }
}
