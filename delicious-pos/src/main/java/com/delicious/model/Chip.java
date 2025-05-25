package com.delicious.model;

public class Chip implements OrderItem {
    private String type;

    public Chip(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getDescription() {
        return "Chips - " + type;
    }
}