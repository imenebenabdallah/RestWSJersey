package com.pluralsight.invivoo;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
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

@Path("search/activities")
public class ActivitySearchResource {

	static Logger logger = Logger.getLogger("ActivitySearchResource");
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchActivites(@QueryParam(value = "description") List<String> descriptions) {
		System.out.println("web service call done");
		logger.info("searching for: " + descriptions.toString());
		List<Activity> activities = activityRepository.findByDescriptions(descriptions);
		if (activities == null || activities.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		// return Response.status(Status.OK).entity(new
		// GenericEntity<List<Activity>>(activities) {
		// }).build();
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();

	}

	@GET
	@Path("/getParams")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchActivity(@QueryParam(value = "description") String description) {
		System.out.println("web service call done");
		logger.info("searching for: " + description);
		Activity activity = (Activity) activityRepository.findByDescription(description);
		if (activity == null) {
			System.out.println(("null activity"));
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("found activity: "+activity.toString());
		return Response.ok().entity(Activity.class).build();

	}
	


}
