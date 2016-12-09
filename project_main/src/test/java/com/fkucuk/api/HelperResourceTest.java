package com.fkucuk.api;

import com.fkucuk.HelperResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fat on 09.12.2016.
 */
public class HelperResourceTest {
    HelperResource hr;
    public HelperResourceTest(){
        hr = new HelperResource();
    }

    @Test
    public void WHEN_CalculateBMI_SHOULD_BeCorrect(){
        float result  = hr.calculateBmi(187, 95);
        Assert.assertEquals(27.16692, result, 0.05);
    }

}
