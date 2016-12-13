package com.fkucuk.repository;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by fat on 13.12.2016.
 */
public class HelperRepositoryTest {
    HelperRepository helperRepository = new HelperRepository();
    @Test
    public void WHEN_GetDemoKey_SR_DemoKey(){
        Assert.assertThat(helperRepository.getUSDAApiKey(), anyOf(is("EE7YTGjjN3J9IoDWjK1OrbE3MlkWWqWVhmmCWEsh")
                , is("D4q3Mo9WPvxgYzxBwFQRnYEe9zv3cyDkLV4obagr")));
    }

}
