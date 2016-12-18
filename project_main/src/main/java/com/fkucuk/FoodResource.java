package com.fkucuk;

import com.fkucuk.model.Food;
import com.fkucuk.repository.FoodRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path(value = "foods")
public class FoodResource {

    FoodRepository foodRepository = new FoodRepository();

    @GET
    public Response searchFood(@QueryParam(value = "keyword") String keyword){
        return foodRepository.searchFood(keyword);
    }

    @GET
    @Path("{foodId}")
    public Response getFood(@PathParam("foodId")String foodId ){

        Food food = foodRepository.getFoodData(foodId);

        if (food == null){
            Response.noContent().build();
        }
        return Response.ok().entity(food)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }


}
