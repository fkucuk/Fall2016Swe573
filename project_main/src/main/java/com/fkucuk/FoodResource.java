package com.fkucuk;

import com.fkucuk.model.FoodSearchResult;
import com.fkucuk.repository.FoodRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by fat on 28.11.2016.
 */
@Path(value = "foods")
public class FoodResource {

FoodRepository foodRepository = new FoodRepository();

    @GET
    public Response searchFood(@QueryParam(value = "foodGroup") String foodGroup
            ,@QueryParam(value = "keyword")  String keyword){
        return foodRepository.searchFood(keyword, foodGroup);
    }

}
