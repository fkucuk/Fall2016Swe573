package com.fkucuk;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by fat on 28.11.2016.
 */
@Path("helper")
public class HelperResource {

    @GET
    @Path("bmi")
    public float calculateBmi(@QueryParam(value = "height")float height,@QueryParam(value = "weight") float weight){
        return 0;
    }


}
