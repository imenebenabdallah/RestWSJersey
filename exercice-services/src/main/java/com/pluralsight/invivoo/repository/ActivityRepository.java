package com.pluralsight.invivoo.repository;

import java.util.List;

import com.pluralsight.invivoo.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(String activityId);

	void createActivity(Activity activity);

	void update(Activity activity);

	void delete(String activityId);

	List<Activity> findByDescriptions(List<String> descriptions);

	Activity findByDescription(String description);

}