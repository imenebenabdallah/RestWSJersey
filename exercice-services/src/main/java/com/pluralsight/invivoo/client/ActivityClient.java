package com.pluralsight.invivoo.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.invivoo.model.Activity;

public class ActivityClient {

	private Client client;

	ActivityClient() {
		client = ClientBuilder.newClient();
	}

	public Activity getActivity(String id) {
		// http://localhost:8080/exercise-services/webapi/activities/1234
		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");

		// de cet url on veut lancer une commande GET(HTTP) pour obtenir un
		// objet de type Activity
		// Activity response =
		// target.path("activities/").path("getActivity/"+id).request().get(Activity.class);
		Activity response = target.path("activities/").path("getActivity/" + id).request(MediaType.APPLICATION_JSON)
				.get(Activity.class);

		return response;

	}

	public List<Activity> getAllActivities() {

		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");
		List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {
				});
		return response;
	}

	/************ Working with Response Object from server *************/

	public Activity getActivityResponse(String id) {
		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");

		// de cet url on veut lancer une commande GET(HTTP) pour obtenir un
		// objet de type Activity
		Response response = target.path("activities/").path("getActivityResponse/" + id)
				.request(MediaType.APPLICATION_JSON).get(Response.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": Thsere was an error on the server!");
		}

		return response.readEntity(Activity.class);

	}

	/********************** Create activity with POST ************************/

	public Activity createActivity(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");
		Response response = target.path("activities/activity").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": Thsere was an error on the server!");
		}

		return response.readEntity(Activity.class);
	}

	/********************** Update activity **********************/

	public Activity update(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");
		Response response = target.path("activities/" + activity.getId()).request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": Thsere was an error on the server!");
		}

		return response.readEntity(Activity.class);
	}

	
	/********************** Delete activity **********************/

	public void delete(String activityId) {
		WebTarget target = client.target("http://localhost:8080/exercice-services/webapi/");

		Response response = target.path("activities/" + activityId).request(MediaType.APPLICATION_JSON).delete();

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
	}

}
