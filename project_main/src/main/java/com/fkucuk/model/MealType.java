package com.fkucuk.model;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.HashMap;
import java.util.Map;

public enum MealType {
    BREAKFAST(1), LUNCH(2), DINNER(3), SNACK(4);

    private final int value;

    MealType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    private static final Map<Integer, MealType> intToTypeMap = new HashMap<>();
    static {
        for (MealType type : MealType.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    public static MealType fromInt(int i) {
        MealType type = intToTypeMap.get(i);
        if (type == null)
            throw new NO_IMPLEMENT();
        return type;
    }
}
