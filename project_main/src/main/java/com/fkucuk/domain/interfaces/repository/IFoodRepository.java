package com.fkucuk.domain.interfaces.repository;

import com.fkucuk.model.FoodSearchResult;

import javax.ws.rs.core.Response;

/**
 * Created by FATIH.KUCUK on 1.12.2016.
 */
public interface IFoodRepository {

    Response searchFood(String keyword, String foodGroup);
}
