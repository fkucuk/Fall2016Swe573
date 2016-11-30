package com.fkucuk.repository;

import java.util.ArrayList;
import java.util.List;

import com.fkucuk.domain.interfaces.repository.ActivityRepository;
import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearch;
import com.fkucuk.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	@Override
	public List<Activity> findByConstraints(ActivitySearch search) {
		
		System.out.println(search.getDurationTo());
		System.out.println(search.getSearchType());
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("swimming");
		activity.setDuration(55);
		
		activities.add(activity);
		
		return activities;
	}
	
	@Override
	public List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo) {
		// select * from activities where description in (?,?,?) and duration > ? and duration < ?
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("swimming");
		activity.setDuration(55);
		
		activities.add(activity);
		
		return activities;
	}
	
	@Override
	public void create(Activity activity) {
		//should issue a insert statement to the db
	}
	
	@Override
	public void delete(String activityId) {
		//delete from activity where activityid = ?
	}
	
	@Override
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
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		
		activities.add(activity2);
		
		return activities;
	}
	
	@Override
	public Activity findActivity(String activityId) {
		
		if(activityId.equals("7777")) {
			return null;
		}
		
		Activity activity1 = new Activity();
		
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		
		User user = new User();
		user.setId(5678);
		user.setName("Bryan");
		
		activity1.setUser(user);
		
		return activity1;
	}
	
}
