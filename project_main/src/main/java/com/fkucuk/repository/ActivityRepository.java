package com.fkucuk.repository;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearchResult;
import com.fkucuk.model.UserActivity;
import org.sql2o.Connection;

import java.util.List;


public class ActivityRepository {



    public int addUserActivity(UserActivity userActivity) {

        final String sqlInsert = "INSERT INTO UserActivity (UserId, ActivityId, Duration, Moment) " +
                "VALUES (:userId, :activityId, :duration, :moment)";

        try(Connection conn = DbHelper.getSql2o().open()) {
            return conn.createQuery(sqlInsert)
                    .addParameter("userId", userActivity.getUserId())
                    .addParameter("activityId", userActivity.getActivity().getActivityId())
                    .addParameter("duration", userActivity.getDuration())
                    .addParameter("moment", userActivity.getMoment())
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }

    public List<ActivitySearchResult> searchActivities(String keyword) {
        keyword = '%' + keyword + '%';

        final String sql = "select ActivityId, Description from Activity " +
                "WHERE Description LIKE :keyword OR :keyword = '%-1%'";
        try(Connection conn = DbHelper.getSql2o().open()) {

            return conn.createQuery(sql)
                    .addParameter("keyword", keyword)
                    .executeAndFetch(ActivitySearchResult.class);
        }
    }

    public Activity getActivity(Integer activityId) {
        final String sql = "select ActivityId, Description, Cat3 CalorieBurnPerHour from Activity " +
                "WHERE ActivityId = :activityId";
        try(Connection conn = DbHelper.getSql2o().open()) {

            return conn.createQuery(sql)
                    .addParameter("activityId", activityId)
                    .executeAndFetchFirst(Activity.class);

        }
    }


}
