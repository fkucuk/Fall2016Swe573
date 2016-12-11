package com.fkucuk.repository;

import java.util.List;

public class Address {
    public String street;
    public String town;
    public List<City> cities;

    public Address(){}

    public Address(String street, String town, List<City> cities) {
        this.street = street;
        this.town = town;
        this.cities = cities;
    }
}