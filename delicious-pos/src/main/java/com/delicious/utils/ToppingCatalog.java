package com.delicious.utils;

import com.delicious.model.enums.ToppingType;

import java.util.*;

public class ToppingCatalog {
    private static final Map<String, ToppingType> TOPPING_MAP = new TreeMap<>();

    static {
        add("Steak", ToppingType.MEAT);
        add("Ham", ToppingType.MEAT);
        add("Salami", ToppingType.MEAT);
        add("Roast Beef", ToppingType.MEAT);
        add("Chicken", ToppingType.MEAT);
        add("Bacon", ToppingType.MEAT);

        add("American", ToppingType.CHEESE);
        add("Provolone", ToppingType.CHEESE);
        add("Cheddar", ToppingType.CHEESE);
        add("Swiss", ToppingType.CHEESE);
        add("Mozzarella", ToppingType.CHEESE);

        add("Lettuce", ToppingType.REGULAR);
        add("Peppers", ToppingType.REGULAR);
        add("Onions", ToppingType.REGULAR);
        add("Tomatoes", ToppingType.REGULAR);
        add("Jalapenos", ToppingType.REGULAR);
        add("Cucumbers", ToppingType.REGULAR);
        add("Pickles", ToppingType.REGULAR);
        add("Guacamole", ToppingType.REGULAR);
        add("Mushrooms", ToppingType.REGULAR);
        add("Banana Peppers", ToppingType.REGULAR);

        add("Mayo", ToppingType.SAUCE);
        add("Mustard", ToppingType.SAUCE);
        add("Ketchup", ToppingType.SAUCE);
        add("Ranch", ToppingType.SAUCE);
        add("Thousand Islands", ToppingType.SAUCE);
        add("Chipotle Mayo", ToppingType.SAUCE);
        add("Vinaigrette", ToppingType.SAUCE);

        add("Au Jus", ToppingType.SIDE);
        add("Sauce", ToppingType.SIDE);
    }

    private static void add(String name, ToppingType type) {
        TOPPING_MAP.put(name, type);
    }

    public static boolean isValidTopping(String name) {
        return TOPPING_MAP.containsKey(normalize(name));
    }

    public static ToppingType getToppingType(String name) {
        return TOPPING_MAP.get(normalize(name));
    }

    public static Map<String, ToppingType> getAllToppings() {
        return TOPPING_MAP;
    }

    public static String getNormalizedTopping(String name) {
        return TOPPING_MAP.keySet().stream()
                .filter(k -> k.equalsIgnoreCase(name))
                .findFirst()
                .orElse(name);
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