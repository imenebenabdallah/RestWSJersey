package com.pluralsight.invivoo;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.invivoo.model.Activity;
import com.pluralsight.invivoo.model.User;
import com.pluralsight.invivoo.repository.ActivityRepository;
import com.pluralsight.invivoo.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {
	//a comment
	static Logger logger = Logger.getLogger("ActivityResource");
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getActivityRepository() {// http:localhost:8080/exercise-services/webapi/activites
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getActivity/{activityId}") // http:localhost:8080/exercise-services/webapi/activites/getActivity/1234
	public Activity getActivity(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getActivity/{activityId}/user") // http:localhost:8080/exercise-services/webapi/activites/getActivity/1234/user
	public User getActivityUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}

	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		if (formParams != null && !formParams.isEmpty()) {
			logger.info(formParams.getFirst("description"));
			logger.info(formParams.getFirst("duration"));
			Activity activity = new Activity();
			activity.setDescription(formParams.getFirst("description"));
			activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
			activityRepository.createActivity(activity);
			return activity;
		} else
			logger.info("empty");
		return null;

	}

	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityJSON(Activity activity) {
		if (activity != null) {
			logger.info(activity.getDescription());
			logger.info("duration:" + activity.getDuration());

			activityRepository.createActivity(activity);

			return activity;
		} else
			logger.info("empty");
		return null;

	}

	/************** Using Response Object ***************/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getActivityResponse/{activityId}") // http:localhost:8080/exercise-services/webapi/activites/getActivity/1234
	public Response getActivityresponse(@PathParam("activityId") String activityId) {
		if (activityId == null || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		logger.info("getting activity Id:" + activityId);
		Activity activity = activityRepository.findActivity(activityId);
		if (activity == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.status(Status.OK).entity(activity).build();
	}

	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Activity activity) {
		if (activity != null) {
			logger.info("updating activity with ID: " + activity.getId());

			activityRepository.update(activity);

			return Response.ok().entity(activity).build();
		} else
			logger.info("empty");
		return null;

	}
	
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete (@PathParam ("activityId") String activityId) {
		logger.info(activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
	
}
