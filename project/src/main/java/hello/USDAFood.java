package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fat on 20.11.2016.
 */

@RestController
public class USDAFood {

    private static final String USDA_API_KEY = "D4q3Mo9WPvxgYzxBwFQRnYEe9zv3cyDkLV4obagr";

/*    @RequestMapping("/food")
    public String food(@RequestParam(value="keyword") String keyword){
        return "http://api.nal.usda.gov/ndb/search/?format=json&q="+
                        keyword
                        +"&sort=n&max=25&offset=0&api_key="
                        + USDA_API_KEY;
    }*/


    @RequestMapping("/food")
    public String food(@RequestParam(value="keyword") String keyword){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.nal.usda.gov/ndb/search/?format=json&q={keyword}" +
                        "&sort=n&max=25&offset=0&api_key={USDA_API_KEY}"
                , String.class
                , keyword
                , USDA_API_KEY);
    }
}

