package com.fkucuk.repository;

import com.fkucuk.model.*;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.sql2o.Connection;
import org.sql2o.Query;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodRepository  {

    public Response searchFood(String keyword, String foodGroup) {


        System.out.println("geldi");

        System.out.println(System.getProperty("RDS_HOSTNAME").toString());
        Client client = ClientBuilder.newClient();

        return client.target("http://api.nal.usda.gov/ndb")
                .path("search")
                .queryParam("format", "json")
                .queryParam("q", "butter")
                .queryParam("api_key", "DEMO_KEY")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get(Response.class);
    }


    public UserMeal getUserMeal(int userId, int mealDay){
        return getUserMeal(userId, mealDay, mealDay);
    }


    public UserMeal getUserMeal(int userId, int startDay, int endDay){

        final String sql = "select * " +
                "from UserMeal um " +
                "JOIN MealFood mf ON mf.UserMealId = um.UserMealId " +
                "JOIN Food f ON f.FoodId = mf.FoodId " +
                "WHERE um.UserId = :userId \n" +
                "AND MealDay between :startDay and :endDay";



        try(Connection con = DbHelper.getSql2o().open()) {
            List<Map<String,Object>> query =  con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("startDay", startDay)
                    .addParameter("endDay", endDay)
                    .executeAndFetchTable().asList();


            Integer userMealId = null;
            List<Meal> meals = new ArrayList<>();
            Meal m ;
            List<FoodConsumption> foodConsumptions = new ArrayList<>();


            for (Map<String, Object> row: query
                    ) {


                if (userMealId != row.get("usermealid")) {
                    userMealId = (Integer) row.get("usermealid");
                    m = new Meal(MealType.fromInt((Integer)row.get("mealtypeid")) , (Integer)row.get("mealday"));
                    m.setMealId(userMealId);
                    meals.add(m);
                    foodConsumptions = new ArrayList<>();
                    m.setFoodConsumptions(foodConsumptions);
                }

                Food food = new Food();
                food.setId(Integer.parseInt(row.get("foodid").toString()));
                food.setName(row.get("foodname").toString());
                food.setJsonData(row.get("fooddata").toString());

                FoodUnit foodUnit = new FoodUnit();
                foodUnit.setName(row.get("unit").toString());
                float quantity = (Float)(row.get("quantity"));

                foodConsumptions.add( new FoodConsumption(food, quantity, foodUnit));

            }
            return new UserMeal(userId, meals);
        }
    }


    public int addUserMeal(int userId, Meal meal) {

        final String sqlExists = "SELECT UserMealId FROM UserMeal "+
                "WHERE MealTypeId = :mealTypeId " +
                " AND MealDay = :mealDay AND UserId = :userId";

        final String sqlInsert = "INSERT INTO UserMeal (UserId, MealTypeId, MealDay) " +
                "VALUES (:userId, :mealTypeId, :mealDay)";

        final String sqlFoods = "INSERT INTO MealFood (UserMealId, FoodId, Unit, Quantity) " +
                "VALUES (:userMealId, :foodId, :unit, :quantity)";

        Integer userMealId;

        try(Connection conn = DbHelper.getSql2o().beginTransaction()){

            userMealId =  conn.createQuery(sqlExists)
                    .addParameter("mealTypeId", meal.getMealType().getValue())
                    .addParameter("mealDay", meal.getMealDay())
                    .addParameter("userId", userId)
                    .executeScalar(Integer.class);


            if (userMealId == null){
                userMealId = conn.createQuery(sqlInsert)
                        .addParameter("userId", userId)
                        .addParameter("mealTypeId", meal.getMealType().getValue())
                        .addParameter("mealDay", meal.getMealDay())
                        .executeUpdate()
                        .getKey(Integer.class);
            }

            if(meal.getFoodConsumptions() != null){


                Query foodQuery = conn.createQuery(sqlFoods);

                for (FoodConsumption f : meal.getFoodConsumptions()
                        ) {
                    foodQuery.addParameter("userMealId", userMealId)
                            .addParameter("foodId", f.getFood().getId())
                            .addParameter("unit", f.getUnit().getName())
                            .addParameter("quantity", f.getQuantity())
                            .addToBatch();
                }

                foodQuery.executeBatch();
            }

            conn.commit();
        }

        return userMealId;
    }

    public int createUserMeal(int userId, int mealTypeId, int day) {
        String sql = "INSERT INTO UserMeal (UserId, MealTypeId, MealDay) " +
                "VALUES (:userId, :mealTypeId, :mealDay)";
        int result;
        try(Connection con = DbHelper.getSql2o().open()){
            result = con.createQuery(sql)
                    .addParameter(":userId", userId)
                    .addParameter(":mealTypeId", mealTypeId)
                    .addParameter(":mealDay", day)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
        return result;

    }


}
