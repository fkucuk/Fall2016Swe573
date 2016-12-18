package com.fkucuk.domain.interfaces.repository;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearchResult;
import com.fkucuk.model.UserActivity;

import java.util.List;

/**
 * Created by fat on 18.12.2016.
 */
public interface IActivityRepository {
    int addUserActivity(UserActivity userActivity);

    List<ActivitySearchResult> searchActivities(String keyword);

    Activity getActivity(Integer activityId);

    List<UserActivity> getUserActivities(int userId, int day);
}
