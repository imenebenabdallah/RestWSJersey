package com.pluralsight.invivoo.client;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.pluralsight.invivoo.model.Activity;
import com.pluralsight.invivoo.model.ActivitySearch;

public class ActivitySearchClient {
	Logger logger = Logger.getLogger("ActivitySearchClient");

	private Client client;

	public ActivitySearchClient() {
		client = ClientBuilder.newClient();
	}

	List<Activity> search(String param, List<String> searchValues) {
		// http://localhost:8080/exercise-services/webapi//search/activities?description=swimming&description=running

		URI uri = UriBuilder.fromUri("http://localhost:8080/exercice-services/webapi").path("search/activities").path("getParams")
				.queryParam(param, searchValues).build();
		System.out.println(uri);
		WebTarget target = client.target(uri);
		// Response response =
		// target.request(MediaType.APPLICATION_JSON).get(Response.class);
		// if (response.getStatus() != 200)
		// throw new RuntimeException(response.getStatus() + ": Thsere was an
		// error on the server!");

		List<Activity> activities = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {
		});

		return activities;
	}

	Activity searchActivity(String param, String searchValue) {
		// http://localhost:8080/exercise-services/webapi//search/activities?description=swimming&description=running

		URI uri = UriBuilder.fromUri("http://localhost:8080/exercice-services/webapi").path("search/activities")
				.queryParam(param, searchValue).build();
		System.out.println(uri);
		WebTarget target = client.target(uri);
		Activity response = target.request(MediaType.APPLICATION_JSON).get(Activity.class);
		return response;
		// if (response.getStatus() != 200)
		// throw new RuntimeException(response.getStatus() + ": There was an
		// error on the server!");
		// logger.info(response.toString());
		// return response.readEntity(Activity.class);

	}

	public List<Activity> searchParam(String param, List<String> searchValues) {

		// http://localhost:8080/exercise-services/webapi//search/activities?description=swimming&description=running

		URI uri = UriBuilder.fromUri("http://localhost:8080/exercice-services/webapi").path("search/activities")
				.queryParam(param, searchValues).build();

		WebTarget target = client.target(uri);

		List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});

		System.out.println(response);

		return response;

	}
	
	/*************** Search by Criteria******************/
	//client et serveur se mettent en accord sur plus qu'un URL
	//Criteria est plus forte que des paramètres
	//Criteria est utilisée avec un POST

	public List<Activity> search(ActivitySearch search) {
		URI uri = UriBuilder.fromUri("http://localhost:8080/exercice-services/webapi").path("search/activities")
				.build();

		WebTarget target = client.target(uri);

		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(search, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(new GenericType<List<Activity>>() {});
	}
}
