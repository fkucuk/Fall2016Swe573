package com.fkucuk.domain.interfaces.repository;

import com.fkucuk.model.User;

/**
 * Created by fat on 18.12.2016.
 */
public interface IUserRepository {
    User createUser(User user);

    User getUser(int userId);

    User updateUser(User user);

    User getUserByEmail(String email);

    int getUserIdByEmail(String email);

    boolean authenticate(String username, String password);
}
