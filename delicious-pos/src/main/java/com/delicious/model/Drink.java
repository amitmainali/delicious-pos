package com.delicious.model;

import com.delicious.model.enums.Size;

public class Drink implements OrderItem {
    private Size size;
    private String flavor;

    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
            default -> 0.00;
        };
    }

    @Override
    public String getDescription() {
        return String.format("%s Drink - %s", formatSize(size), flavor);
    }

    private String formatSize(Size size) {
        return switch (size) {
            case SMALL -> "Small";
            case MEDIUM -> "Medium";
            case LARGE -> "Large";
            default -> "";
        };
    }
}