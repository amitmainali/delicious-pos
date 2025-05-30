package com.delicious.model;

import com.delicious.model.enums.ToppingType;

import java.util.*;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();

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
        sb.append(String.format("%-4s %-6s %-40s %6s\n", "Qty", "Size", "Item", "Price"));
        sb.append("---------------------------------------------------------------\n");

        for (OrderItem item : items) {
            if (item instanceof Sandwich sandwich) {
                String label = cleanItemNameFromDescription(sandwich.getDescription().split("\n")[0]);
                String size = extractSizeFromDescription(sandwich.getDescription());
                double price = sandwich.getPrice();

                sb.append(String.format("%-4d %-6s %-40s $%5.2f\n", 1, size, label, price));
                String indent = "                ";

                Map<ToppingType, List<String>> grouped = new LinkedHashMap<>();
                for (ToppingType type : ToppingType.values()) grouped.put(type, new ArrayList<>());

                for (Topping topping : sandwich.getToppings()) {
                    String name = topping.getName();
                    if (topping.isExtra()) name += " (extra)";
                    grouped.get(topping.getType()).add(name);
                }

                for (ToppingType type : ToppingType.values()) {
                    List<String> items = grouped.get(type);
                    if (!items.isEmpty()) {
                        sb.append(indent).append(String.format("- %s: %s\n",
                                capitalize(type.name()), String.join(", ", items)));
                    }
                }
            } else {
                String desc = item.getDescription().replace("\n", " ").trim();
                String size = extractSizeFromDescription(desc);
                String name = cleanItemNameFromDescription(desc);
                sb.append(String.format("%-4d %-6s %-40s $%5.2f\n", 1, size, name, item.getPrice()));
            }
        }

        sb.append("---------------------------------------------------------------\n");
        sb.append(String.format("%-52s $%5.2f", "Total:", getTotalPrice()));
        return sb.toString();
    }

    private String capitalize(String word) {
        return word.charAt(0) + word.substring(1).toLowerCase();
    }

    private String extractSizeFromDescription(String description) {
        if (description.startsWith("4\"")) return "4\"";
        if (description.startsWith("8\"")) return "8\"";
        if (description.startsWith("12\"")) return "12\"";
        if (description.toLowerCase().startsWith("small")) return "S";
        if (description.toLowerCase().startsWith("medium")) return "M";
        if (description.toLowerCase().startsWith("large")) return "L";
        return "";
    }

    private String cleanItemNameFromDescription(String description) {
        String[] sizeMarkers = {"4\"", "8\"", "12\"", "Small", "Medium", "Large"};
        for (String marker : sizeMarkers) {
            if (description.startsWith(marker)) {
                return description.substring(marker.length()).trim();
            }
        }
        return description.trim();
    }
}