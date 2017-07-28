package com.pluralsight.invivoo.client;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.pluralsight.invivoo.model.Activity;

public class ActivityClientTest {

	Logger logger = Logger.getLogger("ActivityClientTest");

	@Test
	public void testGetActivity() {
		ActivityClient activityClient = new ActivityClient();
		Activity activity = activityClient.getActivity("1234");
		logger.info(activity.toString());
		assertNotNull(activity);
	}

	@Test
	public void testALLActivities() {
		ActivityClient activityClient = new ActivityClient();
		List<Activity> activities = activityClient.getAllActivities();
		assertNotNull(activities);
	}

	@Test
	public void testGetActivityAsResponse() {
		ActivityClient activityClient = new ActivityClient();
		Activity activity = activityClient.getActivityResponse("1234");
		logger.info(activity.toString());
		assertNotNull(activity);
	}

	@Test(expected = RuntimeException.class)
	public void testGetWithBadRequest() {
		ActivityClient client = new ActivityClient();
		client.getActivityResponse("123");
	}

	@Test(expected = RuntimeException.class)
	public void testGetWithNotFound() {
		ActivityClient client = new ActivityClient();
		client.getActivityResponse("1239");
	}

	@Test
	public void testCreate() {
		Activity activity = new Activity();
		activity.setDuration(50);
		activity.setId("2015");
		activity.setDescription("playing on the playstation");
		ActivityClient client = new ActivityClient();
		Activity response = client.createActivity(activity);
		assertNotNull(response);
	}

	@Test
	public void testUpdate() {
		Activity activity = new Activity();
		activity.setDuration(50);
		activity.setId("2015");
		activity.setDescription("playing on the playstation");
		ActivityClient client = new ActivityClient();
		Activity response = client.update(activity);
		logger.info(response.toString());
		assertNotNull(response);
	}

	@Test
	public void delete() {
		ActivityClient client = new ActivityClient();
		client.delete("1235");
	}
}
