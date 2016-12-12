package com.fkucuk.model;

/**
 * Created by fat on 11.12.2016.
 */
public class ActivitySearchResult {
    private int activityId;
    private String description;

    public ActivitySearchResult(){}

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
