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
}
