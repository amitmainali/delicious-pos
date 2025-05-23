package com.delicious.model;

import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;
import com.delicious.model.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private Size size;
    private BreadType breadType;
    private boolean toasted;
    private List<String> meats;
    private List<String> cheeses;
    private List<String> regularToppings;
    private List<String> sauces;
    private List<String> sides;

    public Sandwich(Size size, BreadType breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
    }

    public void addMeat(String meat) {
        meats.add(meat);
    }

    public void addCheese(String cheese) {
        cheeses.add(cheese);
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    public void addSide(String side) {
        sides.add(side);
    }

    @Override
    public double getPrice() {
        double basePrice = switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
            default -> 0.00;
        };

        double meatPrice = switch (size) {
            case FOUR_INCH -> meats.size() * 1.00;
            case EIGHT_INCH -> meats.size() * 2.00;
            case TWELVE_INCH -> meats.size() * 3.00;
            default -> 0.00;
        };

        double cheesePrice = switch (size) {
            case FOUR_INCH -> cheeses.size() * 0.75;
            case EIGHT_INCH -> cheeses.size() * 1.50;
            case TWELVE_INCH -> cheeses.size() * 2.25;
            default -> 0.00;
        };

        return basePrice + meatPrice + cheesePrice;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(switch (size) {
            case FOUR_INCH -> "4\"";
            case EIGHT_INCH -> "8\"";
            case TWELVE_INCH -> "12\"";
            default -> "";
        });
        sb.append(" ").append(breadType.name().charAt(0)).append(breadType.name().substring(1).toLowerCase()).append(" Sandwich");

        if (toasted) sb.append(" (Toasted)");

        if (!meats.isEmpty()) sb.append("\n  Meats: ").append(String.join(", ", meats));
        if (!cheeses.isEmpty()) sb.append("\n  Cheeses: ").append(String.join(", ", cheeses));
        if (!regularToppings.isEmpty()) sb.append("\n  Toppings: ").append(String.join(", ", regularToppings));
        if (!sauces.isEmpty()) sb.append("\n  Sauces: ").append(String.join(", ", sauces));
        if (!sides.isEmpty()) sb.append("\n  Sides: ").append(String.join(", ", sides));

        return sb.toString();
    }
}