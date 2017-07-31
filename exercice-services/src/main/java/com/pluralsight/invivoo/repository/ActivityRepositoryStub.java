package com.pluralsight.invivoo.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.invivoo.model.Activity;
import com.pluralsight.invivoo.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pluralsight.invivoo.repository.ActivityRepository#findAllActivities()
	 */

	static List<Activity> activities;

	static {
		User user = new User();
		user.setId("4567");
		user.setName("Bryan");
		activities = new ArrayList<Activity>();
		Activity activity1 = new Activity();

		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		activity1.setUser(user);
		activities.add(activity1);

		Activity activity2 = new Activity();

		activity2.setId("1235");
		activity2.setDescription("dance");
		activity2.setDuration(50);
		activities.add(activity2);

		Activity activity3 = new Activity();

		activity3.setId("1236");
		activity3.setDescription("gymnastique");
		activity3.setDuration(60);
		activity3.setUser(user);
		activities.add(activity3);

		Activity activity4 = new Activity();

		activity4.setId("1237");
		activity4.setDescription("handball");
		activity4.setDuration(60);
		activities.add(activity4);

	}

	@Override
	public List<Activity> findAllActivities() {
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
		int i = 0;
		while (i < activities.size()) {
			if (activities.get(i).getId().equals(activityId)) {
				return activities.get(i);
			}
			i++;

		}
		return null;

	}

	private void getActivity() {
		Activity a = activities.get(0);
	}

	@Override
	public void createActivity(Activity activity) {
		activities.add(activity);
	}

	@Override
	public void update(Activity activity) {
		int i = 0;
		boolean found = false;
		Activity foundActivity = new Activity();
		while (i < activities.size() && !found) {
			if (activity.getId().equals(activities.get(i).getId())) {
				found = true;
				foundActivity = activities.get(i);
			}
			i++;
		}
		if (!found) {
			createActivity(activity);
			return;
		}
		foundActivity.setDescription(activity.getDescription());
		foundActivity.setDuration(activity.getDuration());
		foundActivity.setUser(activity.getUser());
	}

	@Override
	public void delete(String activityId) {
		for (Activity activity : activities) {
			if (activity.getId().equals(activityId)) {
				activities.remove(activity);
				return;
			}
		}
	}

	@Override
	public List<Activity> findByDescriptions(List<String> descriptions) {

		List<Activity> result = new ArrayList<Activity>();
		for (String description : descriptions) {
			for (Activity activity : result) {
				if (activity.getDescription().equals(description)) {
					result.add(activity);
					break;
				}
			}
		}

		return result;
	}

	@Override
	public Activity findByDescription(String description) {
		Activity a = new Activity();
		for (Activity activity : activities) {
			if (activity.getDescription().equals(description))
				return activity;
		}
		return a;
	}
}
