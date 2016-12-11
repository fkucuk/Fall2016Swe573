package com.fkucuk.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Activity {

	private int id;
	private String description;
	private float calorieBurnPerHour;

	public Activity(){}

	public float getCalorieBurnPerHour() {
		return calorieBurnPerHour;
	}

	public void setCalorieBurnPerHour(float calorieBurnPerHour) {
		this.calorieBurnPerHour = calorieBurnPerHour;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
