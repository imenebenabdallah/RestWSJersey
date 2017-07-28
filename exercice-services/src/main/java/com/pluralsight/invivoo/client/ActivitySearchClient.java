package com.pluralsight.invivoo.client;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.pluralsight.invivoo.model.Activity;

public class ActivitySearchClient {
	Logger logger = Logger.getLogger("ActivitySearchClient");
	
	private Client client;

	public ActivitySearchClient() {
		client = ClientBuilder.newClient();
	}

	List<Activity> search(String param, List<String> searchValues) {
		// http://localhost:8080/exercise-services/webapi//search/activities?description=swimming&description=running

		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi").path("search/activities")
				.queryParam(param, searchValues).build();
		WebTarget target = client.target(uri);

		List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});
		logger.info(response.toString());
		return response;
	}
}
