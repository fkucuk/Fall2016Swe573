package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fat on 20.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodResultItem {
    private String name;

    public FoodResultItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodResultItem{"+
                "name='" + name + '\'' +
                '}';
    }
}
