package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fat on 20.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {

    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Food() {
    }
/*
    private FoodSearchResult foodSearchResult;

    public FoodSearchResult getFoodSearchResult() {
        return foodSearchResult;
    }

    public void setFoodSearchResult(FoodSearchResult foodSearchResult) {
        this.foodSearchResult = foodSearchResult;
    }*/

    @Override
    public String toString() {
        return "Food{" +
                "list=" + query + '}';
    }
}
