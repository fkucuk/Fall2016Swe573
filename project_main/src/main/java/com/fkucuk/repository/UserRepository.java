package com.fkucuk.repository;

import com.fkucuk.domain.interfaces.repository.IUserRepository;
import com.fkucuk.model.Activity;
import com.fkucuk.model.Meal;
import com.fkucuk.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by fat on 28.11.2016.
 */
public class UserRepository implements IUserRepository {

    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = emFactory.createEntityManager();

    @Override
    public User createUser(User user) {

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void addUserMeal(int userId, Meal meal) {

    }

    @Override
    public void addUserActivity(int userId, Activity activity) {

    }

    @Override
    public List<Meal> getUserMeals(int userId, int startDate, int endDate) {
        return null;
    }
}
