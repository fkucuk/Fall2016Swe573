package com.fkucuk;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fkucuk.model.Activity;
import com.fkucuk.model.ActivitySearch;
import com.fkucuk.repository.ActivityRepositoryStub;


@Path("search/activities")
public class ActivitySearchResource {

//	private ActivityRepository activityRepository = new ActivityRepositoryStub();
//
//	@POST
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response searchForActivities(ActivitySearch search) {
//		System.out.println(search.getDescriptions() + ", " + search.getDurationFrom());
//
//		List<Activity> activities = activityRepository.findByConstraints(search);
//
//		if(activities == null || activities.size() <= 0) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//
//		return Response.ok().entity(new GenericEntity<List<Activity>> (activities) {}).build();
//
//	}
//
//	@GET
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions,
//			@QueryParam(value = "durationFrom") int durationFrom,
//			@QueryParam(value = "durationTo") int durationTo) {
//
//		System.out.println(descriptions + ", " + durationFrom + ", " + durationTo);
//
//		List<Activity> activities = activityRepository.findByDescription(descriptions, durationFrom, durationTo);
//
//		if(activities == null || activities.size() <= 0) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//
//		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
//	}
	
}
