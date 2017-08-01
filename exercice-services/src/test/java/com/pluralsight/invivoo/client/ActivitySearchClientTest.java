package com.pluralsight.invivoo.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.invivoo.client.ActivitySearchClient;
import com.pluralsight.invivoo.model.Activity;
import com.pluralsight.invivoo.model.ActivitySearch;
import com.pluralsight.invivoo.model.ActivitySearchType;

public class ActivitySearchClientTest {

	@Test
	public void testSearchActivities() {
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
		Activity activity = client.searchActivity(param, description);
		System.out.println(activity);
		assertNotNull(activity);
	}
	
	@Test
	public void testSearchRange() {
		ActivitySearchClient client = new ActivitySearchClient();

		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");

		String secondParam = "durationFrom";
		int durationFrom = 30;

		String thirdParam = "durationTo";
		int durationTo = 55;

		List<Activity> activities = client.search(param, searchValues, secondParam, durationFrom, thirdParam,
				durationTo);

		System.out.println(activities);

		assertNotNull(activities);
	}
	
	@Test
	public void testSearchObject() {

		ActivitySearchClient client = new ActivitySearchClient();

		List<String> searchValues = new ArrayList<String>();
		searchValues.add("biking");
		searchValues.add("running");

		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(searchValues);
		search.setDurationFrom(30);
		search.setDurationTo(55);
		search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);

		List<Activity> activities = client.search(search);

		System.out.println(activities);

		assertNotNull(activities);

	}

}
