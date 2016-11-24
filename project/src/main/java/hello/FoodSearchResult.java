package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fat on 20.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodSearchResult {
    private FoodResultItem[] foodResultItemList;
    public FoodSearchResult() {
    }

    public FoodResultItem[] getFoodResultItemList() {
        return foodResultItemList;
    }

    public void setFoodResultItemList(FoodResultItem[] foodResultItemList) {
        this.foodResultItemList = foodResultItemList;
    }

    @Override
    public String toString() {
        String result = "FoodSearchResult{";
        for (int i = 0; i < foodResultItemList.length; i++) {
            result += '[';
            result += "item=" + foodResultItemList[i] ;
            result += ']';
        }

        result += '}';

        return result;
    }
}
