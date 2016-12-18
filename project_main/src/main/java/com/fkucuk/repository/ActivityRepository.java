package com.fkucuk.repository;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearchResult;
import com.fkucuk.model.UserActivity;
import org.sql2o.Connection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ActivityRepository implements com.fkucuk.domain.interfaces.repository.IActivityRepository {



    @Override
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

    @Override
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

    @Override
    public Activity getActivity(Integer activityId) {
        final String sql = "select ActivityId, Description, Cat3 CalorieBurnPerHour from Activity " +
                "WHERE ActivityId = :activityId";
        try(Connection conn = DbHelper.getSql2o().open()) {

            return conn.createQuery(sql)
                    .addParameter("activityId", activityId)
                    .executeAndFetchFirst(Activity.class);

        }
    }

    @Override
    public List<UserActivity> getUserActivities(int userId, int day){
        
        List<UserActivity> userActivities = new ArrayList<>();
        
        final String sql = "SELECT UserActivityId, u.ActivityId, UserId, Duration, Moment" +
                ", Description activityDesc, Cat3 " +
                ", date_format(Moment, '%Y%m%d') Moment_Formatted\n" +
                "FROM UserActivity u\n" +
                "JOIN Activity a ON a.ActivityId = u.ActivityId \n" +
                "WHERE u.UserId = :userId and date_format(Moment, '%Y%m%d') = :day";
        try(Connection conn = DbHelper.getSql2o().open()) {
            List<Map<String, Object>> userActivityMap = conn.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("day", day)
                    .executeAndFetchTable().asList();

            for (Map<String, Object> row: userActivityMap
                    ) {
                UserActivity userActivity = new UserActivity();

                Activity a = new Activity();
                a.setActivityId((Integer)row.get("activityid"));
                a.setDescription(row.get("activitydesc").toString());
                a.setCalorieBurnPerHour(Float.parseFloat( row.get("cat3").toString()));
                userActivity.setActivity(a);

                userActivity.setDuration((Integer)row.get("duration"));
                userActivity.setUserId((Integer)row.get("userid"));

                userActivity.setMoment((Date)row.get("moment"));


                userActivities.add(userActivity);
            }
            
        }

        return userActivities;
    }


}
