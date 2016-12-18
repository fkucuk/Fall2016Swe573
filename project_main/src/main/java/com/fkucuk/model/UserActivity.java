package com.fkucuk.model;

import java.util.Date;

public class UserActivity {

    private int userId;
    private String userActivityCategory;
    private Activity activity;
    private Date moment;
    private int duration;
    private float caloriesBurned;

    public UserActivity(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getCaloriesBurned() {
        return this.activity.getCalorieBurnPerHour() / 60 * this.duration;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getUserActivityCategory() {
        return userActivityCategory;
    }

    public void setUserActivityCategory(String userActivityCategory) {
        this.userActivityCategory = userActivityCategory;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }
}
