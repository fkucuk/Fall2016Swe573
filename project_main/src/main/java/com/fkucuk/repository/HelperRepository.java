package com.fkucuk.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HelperRepository {
    public String getUSDAApiKey(){
        Map<Boolean, String> demoKeys = new HashMap<>();
        demoKeys.put(true, "EE7YTGjjN3J9IoDWjK1OrbE3MlkWWqWVhmmCWEsh");
        demoKeys.put(false, "D4q3Mo9WPvxgYzxBwFQRnYEe9zv3cyDkLV4obagr");

        Random random = new Random();
        return demoKeys.get(random.nextBoolean());
    }
}
