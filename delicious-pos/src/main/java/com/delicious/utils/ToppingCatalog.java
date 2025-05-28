package com.delicious.utils;

import com.delicious.model.enums.ToppingType;

import java.util.Map;
import java.util.TreeMap;

public class ToppingCatalog {
    private static final Map<String, ToppingType> toppingMap = initializeMap();

    private static Map<String, ToppingType> initializeMap() {
        Map<String, ToppingType> map = new TreeMap<>();

        // Meats
        map.put("Steak", ToppingType.MEAT);
        map.put("Ham", ToppingType.MEAT);
        map.put("Salami", ToppingType.MEAT);
        map.put("Roast Beef", ToppingType.MEAT);
        map.put("Chicken", ToppingType.MEAT);
        map.put("Bacon", ToppingType.MEAT);

        // Cheeses
        map.put("American", ToppingType.CHEESE);
        map.put("Provolone", ToppingType.CHEESE);
        map.put("Cheddar", ToppingType.CHEESE);
        map.put("Swiss", ToppingType.CHEESE);

        // Regular Toppings
        map.put("Lettuce", ToppingType.REGULAR);
        map.put("Peppers", ToppingType.REGULAR);
        map.put("Onions", ToppingType.REGULAR);
        map.put("Tomatoes", ToppingType.REGULAR);
        map.put("Jalapenos", ToppingType.REGULAR);
        map.put("Cucumbers", ToppingType.REGULAR);
        map.put("Pickles", ToppingType.REGULAR);
        map.put("Guacamole", ToppingType.REGULAR);
        map.put("Mushrooms", ToppingType.REGULAR);

        // Sauces
        map.put("Mayo", ToppingType.SAUCE);
        map.put("Mustard", ToppingType.SAUCE);
        map.put("Ketchup", ToppingType.SAUCE);
        map.put("Ranch", ToppingType.SAUCE);
        map.put("Thousand Islands", ToppingType.SAUCE);
        map.put("Vinaigrette", ToppingType.SAUCE);

        // Sides
        map.put("Au Jus", ToppingType.SIDE);
        map.put("Sauce", ToppingType.SIDE);

        return map;
    }

    public static boolean isValidTopping(String name) {
        return toppingMap.containsKey(normalize(name));
    }

    public static ToppingType getToppingType(String name) {
        return toppingMap.get(normalize(name));
    }

    public static Map<String, ToppingType> getAllToppings() {
        return toppingMap;
    }

    private static String normalize(String input) {
        String[] parts = input.trim().toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : parts) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }
}