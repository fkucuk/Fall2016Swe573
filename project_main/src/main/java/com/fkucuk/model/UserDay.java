package com.fkucuk.model;

import java.util.List;

/**
 * Created by fat on 18.12.2016.
 */
public class UserDay {
    private UserMeal userMeal;
    private List<UserActivity> activityList;

    public UserMeal getUserMeal() {
        return userMeal;
    }

    public void setUserMeal(UserMeal userMeal) {
        this.userMeal = userMeal;
    }

    public List<UserActivity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<UserActivity> activityList) {
        this.activityList = activityList;
    }
}
