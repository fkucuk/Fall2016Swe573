package com.fkucuk.repository;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


/**
 * Created by FATIH.KUCUK on 1.12.2016.
 */
public class FoodRepository  {
//    @Override
//    public FoodSearchResult searchFood(String keyword, String foodGroup) {
//        Client client = ClientBuilder.newClient();
//        FoodSearchResult result = client.target("http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=DEMO_KEY")
//                .request()
//                .get(FoodSearchResult.class);
//        return result;
//    }

    public Response searchFood(String keyword, String foodGroup) {

//        ClientConfig cc = new ClientConfig();
//        cc.property(ClientProperties.PROXY_URI, "8.8.8.8:80");
//
//        Client client = JerseyClientBuilder.withConfig(cc).build();
//

//        ClientConfig cc = new ClientConfig();
//        cc.property(ClientProperties.PROXY_URI, "http://128.128.5.100:8080");
//        cc.property(ClientProperties.PROXY_USERNAME, "fatih.kucuk@lcwaikiki.com");
//        cc.property(ClientProperties.PROXY_PASSWORD, "P@ssw0rd28");
//
        System.out.println("geldi");
//        Client client = ClientBuilder.newClient();
//        client.property(ClientProperties.PROXY_URI, "http://128.128.5.100:8080");
//        client.property(ClientProperties.PROXY_USERNAME, "fatih.kucuk@lcwaikiki.com");
//        client.property(ClientProperties.PROXY_PASSWORD, "P@ssw0rd28");
//

//        String config = client.getConfiguration().toString();
//        System.out.println(config);

        ClientConfig cc = new ClientConfig();
        cc.property(ClientProperties.PROXY_URI, "128.128.5.100:8080");
//        cc.property(ClientProperties.PROXY_USERNAME, "fatih.kucuk@lcwaikiki.com");
//        cc.property(ClientProperties.PROXY_PASSWORD, "P@ssw0rd28");
        Client client = JerseyClientBuilder.newBuilder().withConfig(cc).build();




        Response response = client.target("http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=DEMO_KEY")
                .request()
                .get();
        return  response;
    }


//    private RestTemplate createRestTemplate() throws Exception {
//        final String username = "fatih.kucuk@lcwaikiki.com";
//        final String password = "P@ssw0rd28";
//        final String proxyUrl = "128.128.5.100";
//        final int port = 8080;
//
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(
//                new AuthScope(proxyUrl, port),
//                new UsernamePasswordCredentials(username, password));
//
//        HttpHost myProxy = new HttpHost(proxyUrl, port);
//        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//
//        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
//
//        HttpClient httpClient = clientBuilder.build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setHttpClient(httpClient);
//
//        return new RestTemplate(factory);
//    }
}
