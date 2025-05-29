package com.delicious.model.signature;

import com.delicious.model.OrderItem;

public abstract class AbstractSandwich implements OrderItem {
    private final String name;

    protected AbstractSandwich(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}