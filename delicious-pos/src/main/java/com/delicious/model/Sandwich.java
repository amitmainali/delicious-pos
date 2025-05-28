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
    private List<Topping> toppings;

    public Sandwich(Size size, BreadType breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        double basePrice = switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
            default -> 0.00;
        };

        double meatPrice = 0;
        double cheesePrice = 0;

        for (Topping topping : toppings) {
            if (topping.getType() == ToppingType.MEAT) {
                meatPrice += switch (size) {
                    case FOUR_INCH -> topping.isExtra() ? 0.50 : 1.00;
                    case EIGHT_INCH -> topping.isExtra() ? 1.00 : 2.00;
                    case TWELVE_INCH -> topping.isExtra() ? 1.50 : 3.00;
                    default -> 0.00;
                };
            } else if (topping.getType() == ToppingType.CHEESE) {
                cheesePrice += switch (size) {
                    case FOUR_INCH -> topping.isExtra() ? 0.30 : 0.75;
                    case EIGHT_INCH -> topping.isExtra() ? 0.60 : 1.50;
                    case TWELVE_INCH -> topping.isExtra() ? 0.90 : 2.25;
                    default -> 0.00;
                };
            }
        }

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

        for (ToppingType type : ToppingType.values()) {
            List<String> names = new ArrayList<>();
            for (Topping topping : toppings) {
                if (topping.getType() == type) {
                    String name = topping.getName();
                    if (topping.isExtra()) name += " (extra)";
                    names.add(name);
                }
            }
            if (!names.isEmpty()) {
                sb.append("\n  ").append(type.name().charAt(0)).append(type.name().substring(1).toLowerCase()).append(": ").append(String.join(", ", names));
            }
        }

        return sb.toString();
    }

    public List <Topping> getToppings() {
        return toppings;
    }
}