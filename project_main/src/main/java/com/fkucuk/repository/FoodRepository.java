package com.fkucuk.repository;

import com.fkucuk.HelperResource;
import com.fkucuk.model.*;
import com.fkucuk.model.request.FoodLog;
import org.sql2o.Connection;
import org.sql2o.Query;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodRepository  {

    HelperRepository helperRepository;
    HelperResource helperResource;

    public FoodRepository() {
        helperRepository = new HelperRepository();
        helperResource = new HelperResource();
    }

    public Response searchFood(String keyword) {
        Client client = ClientBuilder.newClient();

        return client.target("http://api.nal.usda.gov/ndb")
                .path("search")
                .queryParam("format", "json")
                .queryParam("q", keyword)
                .queryParam("sort", "r")
                .queryParam("max", "100")
                .queryParam("api_key", helperRepository.getUSDAApiKey())
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
                "JOIN Unit u ON u.UnitID = mf.UnitRef " +
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
                food.setFoodId(row.get("foodid").toString());
                food.setFoodName(row.get("foodname").toString());
                food.setFoodData((JsonObject) row.get("fooddata"));

                FoodUnit foodUnit = new FoodUnit();
                foodUnit.setName(row.get("symbol").toString());
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
                            .addParameter("foodId", f.getFood().getFoodId())
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

    public void logUserFood(int userId, FoodLog foodLog) {

        final String sqlExists = "SELECT UserMealId FROM UserMeal " +
                "WHERE MealTypeId = :mealTypeId " +
                " AND MealDay = :mealDay AND UserId = :userId";

        final String sqlInsert = "INSERT INTO UserMeal (UserId, MealTypeId, MealDay) " +
                "VALUES (:userId, :mealTypeId, :mealDay)";

        final String sqlFoods = "INSERT INTO MealFood (UserMealId, FoodId, UnitRef, Quantity) " +
                "VALUES (:userMealId, :foodId, :unitRef, :quantity)";

        Integer userMealId;

        try (Connection conn = DbHelper.getSql2o().open()) {

            userMealId = conn.createQuery(sqlExists)
                    .addParameter("mealTypeId", foodLog.getMealTypeRef())
                    .addParameter("mealDay", foodLog.getDay())
                    .addParameter("userId", userId)
                    .executeScalar(Integer.class);


            if (userMealId == null) {
                userMealId = conn.createQuery(sqlInsert)
                        .addParameter("userId", userId)
                        .addParameter("mealTypeId", foodLog.getMealTypeRef())
                        .addParameter("mealDay", foodLog.getDay())
                        .executeUpdate()
                        .getKey(Integer.class);
            }

            conn.createQuery(sqlFoods)
                    .addParameter("userMealId", userMealId)
                    .addParameter("foodId", foodLog.getFoodId())
                    .addParameter("unitRef", foodLog.getUnitRef())
                    .addParameter("quantity", foodLog.getQuantity())
                    .executeUpdate();
//            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

    public JsonObject getFoodReportFromUSDA(String foodId) {
        Client client = ClientBuilder.newClient();

        Response response = client.target("http://api.nal.usda.gov/ndb")
                .path("reports")
                .queryParam("api_key", helperRepository.getUSDAApiKey())
                .queryParam("ndbno", foodId)
                .queryParam("type", "f")
                .queryParam("format", "json")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .header("content-type", MediaType.APPLICATION_JSON)
                .get(Response.class);
        if (response.getStatusInfo().equals(Response.Status.OK)) {
            String str =  response.readEntity(String.class);
            JsonReader jsonReader = Json.createReader(new StringReader(str));
            JsonObject object = jsonReader.readObject();
            jsonReader.close();
            return object;
        }
        else
            return null;
    }

    public Food getFoodData(String foodId) {
        Food food;

        final String sql = "SELECT * FROM Food " +
                "WHERE FoodId = :foodId";
        try (Connection conn = DbHelper.getSql2o().open()) {

            List<Map<String, Object>> foodMap = conn.createQuery(sql)
                    .addParameter("foodId", foodId)
                    .executeAndFetchTable().asList();

            if (foodMap.size() > 0){

                String jsonobj = foodMap.get(0).get("fooddata").toString();

                JsonReader jsonReader = Json.createReader(new StringReader(jsonobj));
                JsonObject object = jsonReader.readObject();
                jsonReader.close();

                food = new Food(foodMap.get(0).get("foodid").toString()
                , foodMap.get(0).get("foodname").toString()
                ,object);
            }
            else {

                JsonObject foodData = getFoodReportFromUSDA(foodId);

                String foodName = helperResource.getFoodNameFromFoodReport(foodData);

                food = new Food(foodId, foodName, foodData);
                this.saveFood(food);
            }
        }

        return food;
    }

    private void saveFood(Food food) {
        final String sql = "INSERT INTO Food (FoodId, FoodName, FoodData)" +
                "VALUES (:foodId, :foodName, :foodData)";
        try (Connection conn = DbHelper.getSql2o().open()) {

            conn.createQuery(sql)
                    .addParameter("foodId", food.getFoodId())
                    .addParameter("foodName", food.getFoodName())
                    .addParameter("foodData", food.getJsonString())
                    .executeUpdate();
        }
    }
}
