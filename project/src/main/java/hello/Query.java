package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fat on 21.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    private String queryString;

    public Query() {
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String toString() {
        return "Query{" +
                "q='" + queryString + '\'' +
                '}';
    }
}
