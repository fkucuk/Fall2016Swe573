package com.fkucuk.model;


import java.util.Date;
import java.util.List;



public class User {

	private long userId;
	private String name;
	private String email;
	private String password;
	private float weight;
	private float height;
	private Date birthDate;
	private String activityCat;

	public String getActivityCat() {
		if (this.weight < 60)
			return "Cat1";
		if (this.weight < 70)
			return "Cat2";
		if(this.weight < 80)
			return "Cat3";
		return "Cat4";
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	private Date registrationDate;
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
