package com.fkucuk.model;

public class Activity {

	private int activityId;
	private String description;
	private float calorieBurnPerHour;

	public Activity(){}

	public float getCalorieBurnPerHour() {
		return calorieBurnPerHour;
	}

	public void setCalorieBurnPerHour(float calorieBurnPerHour) {
		this.calorieBurnPerHour = calorieBurnPerHour;
	}

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
