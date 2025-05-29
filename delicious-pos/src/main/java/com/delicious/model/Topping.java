package com.delicious.model;

import com.delicious.model.enums.ToppingType;

public class Topping {
    private final String name;
    private final ToppingType type;
    private final boolean isExtra;

    public Topping(String name, ToppingType type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }
}