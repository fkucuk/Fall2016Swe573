package com.fkucuk;

import java.util.List;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearchResult;
import com.fkucuk.repository.ActivityRepository;


@Path("activities") //http:localhost:8080/webapi/activites
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepository();

	@GET
	public List<ActivitySearchResult> getAllActivities( @QueryParam(value = "keyword") String keyword) {
		return activityRepository.searchActivities(keyword);
	}
	
//	@DELETE
//	@Path("{activityId}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response delete (@PathParam ("activityId") String activityId) {
//		System.out.println(activityId);
//
//		activityRepository.delete(activityId);
//
//		return Response.ok().build();
//	}
	
//	@PUT
//	@Path("{activityId}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response update(Activity activity) {
//
//		System.out.println(activity.getId());
//
//		activity = activityRepository.update(activity);
//
//		return Response.ok().entity(activity).build();
//
//	}


//	@POST
//	@Path("activity")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Activity createActivity(Activity activity) {
//
//		System.out.println(activity.getDescription());
//		System.out.println(activity.getDuration());
//
//		activityRepository.create(activity);
//
//		return activity;
//	}
	
//	@POST
//	@Path("activity")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
//
//		System.out.println(formParams.getFirst("description"));
//		System.out.println(formParams.getFirst("duration"));
//
//		Activity activity = new Activity();
//		activity.setDescription(formParams.getFirst("description"));
//		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
//
//		activityRepository.create(activity);
//
//		return activity;
//	}
	

	
	@GET
	@Path("{activityId}") //http:localhost:8080/webapi/activites/1234
	public Response getActivity(@PathParam ("activityId") Integer activityId) {
		if(activityId == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.getActivity(activityId);
		
		if(activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(activity).build();
	}

	
//	@GET
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	@Path("{activityId}/user") //http:localhost:8080/exercise-services/webapi/activites/1234/user
//	public User getActivityUser(@PathParam ("activityId") String activityId) {
//
//		Activity activity = activityRepository.findActivity(activityId);
//		User user = activity.getUser();
//		return user;
//		//return activityRepository.findActivity(activityId).getUser();
//	}
	
}
