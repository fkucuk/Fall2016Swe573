package com.fkucuk.repository;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by FATIH.KUCUK on 1.12.2016.
 */
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

}
