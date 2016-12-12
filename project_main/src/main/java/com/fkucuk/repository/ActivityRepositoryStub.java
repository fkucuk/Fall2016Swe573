package com.fkucuk.repository;

import java.util.ArrayList;
import java.util.List;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearch;
import com.fkucuk.model.User;

public class ActivityRepositoryStub  {


	public List<Activity> findByConstraints(ActivitySearch search) {
		
		System.out.println(search.getDurationTo());
		System.out.println(search.getSearchType());
		
		List<Activity> activities = new ArrayList<>();
		
		Activity activity = new Activity();
		activity.setActivityId(2345);
		activity.setDescription("swimming");
//		activity.setDuration(55);
		
		activities.add(activity);
		
		return activities;
	}
	

	public List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo) {
		// select * from activities where description in (?,?,?) and duration > ? and duration < ?
		
		List<Activity> activities = new ArrayList<>();
		
		Activity activity = new Activity();
		activity.setActivityId(2345);
		activity.setDescription("swimming");
//		activity.setDuration(55);
		
		activities.add(activity);
		
		return activities;
	}
	

	public void create(Activity activity) {
		//should issue a insert statement to the db
	}
	

	public void delete(String activityId) {
		//delete from activity where activityid = ?
	}
	

	public Activity update(Activity activity) {
		//search the database to see if we have an activity with that id already
		//select * from Activity where id = ?
		//if rs size == 0
		//insert into Activity table
		//else
		//update the Activity
		
		return activity;
	}
	
	public List<Activity> findAllActivities () {
		List<Activity> activities = new ArrayList<>();
		
		Activity activity1 = new Activity();
		
		activity1.setDescription("Swimming");
//		activity1.setDuration(55);
		
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		
		activity2.setDescription("Cycling");
//		activity2.setDuration(120);
		
		activities.add(activity2);
		
		return activities;
	}
	

	public Activity findActivity(String activityId) {
		
		if(activityId.equals("7777")) {
			return null;
		}
		
		Activity activity1 = new Activity();
		
		activity1.setActivityId(1234);
		activity1.setDescription("Swimming");
//		activity1.setDuration(55);
		
		User user = new User();
		user.setUserId(5678);
		user.setName("Bryan");
		
//		activity1.setUser(user);
		
		return activity1;
	}
	
}
