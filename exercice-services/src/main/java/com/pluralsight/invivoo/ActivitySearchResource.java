package com.pluralsight.invivoo;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.invivoo.model.Activity;
import com.pluralsight.invivoo.repository.ActivityRepository;
import com.pluralsight.invivoo.repository.ActivityRepositoryStub;
import com.pluralsight.invivoo.model.ActivitySearch;

@Path("search/activities")
public class ActivitySearchResource {

	static Logger logger = Logger.getLogger("ActivitySearchResource");
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Path("/getParams")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchActivites(@QueryParam(value = "description") final List<String> descriptions) {
		System.out.println("web service call doneeeee");
		logger.info("searching for: " + descriptions.toString());
		List<Activity> activities = activityRepository.findByDescriptions(descriptions);
		if (activities == null || activities.isEmpty()) {
			System.out.println("activities null");
			return Response.status(Status.NOT_FOUND).build();
		}
		// return Response.status(Status.OK).entity(new
		// GenericEntity<List<Activity>>(activities) {
		// }).build();
		Response response = Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
		System.out.println("response from server: "+response.getStatus());
		return response;

	}

	@GET
	@Path("/getActivityWithParams")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchActivity(@QueryParam(value = "description") String description) {
		System.out.println("web service call done");
		logger.info("searching for: " + description);
		Activity activity = (Activity) activityRepository.findByDescription(description);
		if (activity == null) {
			System.out.println(("null activity"));
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("found activity: " + activity.toString());
		Response response = Response.ok().entity(activity).build();
		System.out.println("response from server: "+response.getStatus());
		return response;

	}

	/********************* Search range *********************/
	@GET
	@Path("/getParamsRange")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions,
			@QueryParam(value = "durationFrom") int durationFrom, @QueryParam(value = "durationTo") int durationTo) {

		System.out.println(descriptions + ", " + durationFrom + ", " + durationTo);

		List<Activity> activities = activityRepository.findByDescription(descriptions, durationFrom, durationTo);

		if (activities == null || activities.size() <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
		}).build();
	}

	
	/****************** Search by criteria *************************/
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchForActivities(ActivitySearch search) {
		System.out.println(search.getDescriptions() + ", " + search.getDurationFrom());

		List<Activity> activities = activityRepository.findByConstraints(search);

		if (activities == null || activities.size() <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
		}).build();

	}

}
