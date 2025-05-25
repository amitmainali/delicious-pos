package com.delicious.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    public String buildOrderSummary() {
        StringBuilder sb = new StringBuilder();
        for (OrderItem item : items) {
            sb.append(item.getDescription()).append("\n");
        }
        sb.append("\nTotal: $").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }
}