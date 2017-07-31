package com.pluralsight.invivoo.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.invivoo.model.Activity;

public class ActivitySearchClientTest {

	@Test
	public void testSearch() {
		ActivitySearchClient client = new ActivitySearchClient();
		String param = "description";
		List<String> descriptions = new ArrayList<String>();
		descriptions.add("Swimming");
		descriptions.add("dance");
		List<Activity> activities = client.search(param, descriptions);
		System.out.println(activities);
		assertNotNull(activities);
	}
	
	@Test
	public void testSearchActivityParam() {
		ActivitySearchClient client = new ActivitySearchClient();
		String param = "description";
		String description = "Swimming";
		Activity activities = client.searchActivity(param, description);
		System.out.println(activities);
		assertNotNull(activities);
	}

}
