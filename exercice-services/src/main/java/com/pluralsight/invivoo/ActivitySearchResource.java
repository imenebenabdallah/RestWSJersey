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

@Path("search/activites")
public class ActivitySearchResource {

	static Logger logger = Logger.getLogger("ActivitySearchResource");
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchActivites(@QueryParam(value = "description") List<String> descriptions) {

		logger.info("searching for: " + descriptions.toString());
		List<Activity> activities = activityRepository.findByDescription(descriptions);
		if (activities == null || activities.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(new GenericEntity<List<Activity>>(activities) {}).build();

	}

}
