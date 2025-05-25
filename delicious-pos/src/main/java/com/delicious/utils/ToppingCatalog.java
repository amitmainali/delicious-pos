package com.delicious.utils;

import com.delicious.model.enums.ToppingType;

import java.util.Map;
import java.util.TreeMap;

public class ToppingCatalog {
    private static final Map<String, ToppingType> toppingMap = initializeMap();

    private static Map<String, ToppingType> initializeMap() {
        Map<String, ToppingType> map = new TreeMap<>();

        map.put("steak", ToppingType.MEAT);
        map.put("ham", ToppingType.MEAT);
        map.put("salami", ToppingType.MEAT);
        map.put("roast beef", ToppingType.MEAT);
        map.put("chicken", ToppingType.MEAT);
        map.put("bacon", ToppingType.MEAT);

        map.put("american", ToppingType.CHEESE);
        map.put("provolone", ToppingType.CHEESE);
        map.put("cheddar", ToppingType.CHEESE);
        map.put("swiss", ToppingType.CHEESE);

        map.put("lettuce", ToppingType.REGULAR);
        map.put("peppers", ToppingType.REGULAR);
        map.put("onions", ToppingType.REGULAR);
        map.put("tomatoes", ToppingType.REGULAR);
        map.put("jalapeÃ±os", ToppingType.REGULAR);
        map.put("cucumbers", ToppingType.REGULAR);
        map.put("pickles", ToppingType.REGULAR);
        map.put("guacamole", ToppingType.REGULAR);
        map.put("mushrooms", ToppingType.REGULAR);

        map.put("mayo", ToppingType.SAUCE);
        map.put("mustard", ToppingType.SAUCE);
        map.put("ketchup", ToppingType.SAUCE);
        map.put("ranch", ToppingType.SAUCE);
        map.put("thousand islands", ToppingType.SAUCE);
        map.put("vinaigrette", ToppingType.SAUCE);

        map.put("au jus", ToppingType.SIDE);
        map.put("sauce", ToppingType.SIDE);

        return map;
    }

    public static boolean isValidTopping(String name) {
        return toppingMap.containsKey(name.toLowerCase());
    }

    public static ToppingType getToppingType(String name) {
        return toppingMap.get(name.toLowerCase());
    }

    public static Map<String, ToppingType> getAllToppings() {
        return toppingMap;
    }
}