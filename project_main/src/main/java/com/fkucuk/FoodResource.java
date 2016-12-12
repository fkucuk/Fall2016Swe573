package com.fkucuk;

import com.fkucuk.model.Food;
import com.fkucuk.repository.FoodRepository;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path(value = "foods")
public class FoodResource {

    FoodRepository foodRepository = new FoodRepository();
    HelperResource helperResource = new HelperResource();

    @GET
    public Response searchFood(@QueryParam(value = "keyword")  String keyword){
        return foodRepository.searchFood(keyword);
    }

    @GET
    @Path("{foodId}")
    public Response getFood(@PathParam("foodId")String foodId ){

        Food food = foodRepository.getFoodData(foodId);

        if (food == null){
            Response.noContent().build();
        }
        return Response.ok().entity(food).build();
    }


}
