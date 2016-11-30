package com.fkucuk.repository;

import com.fkucuk.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by FATIH.KUCUK on 30.11.2016.
 */
public class UserRepositoryTest {

    @Test
    public void AddValidUser(){
        User u = new User();
        u.setHeight(100);
        u.setWeight(100);
        u.setName("Fatih");

        UserRepository uRep = new UserRepository();
        uRep.createUser(u);
    }

}
