package com.delicious.ui;

import com.delicious.model.*;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;
import com.delicious.model.enums.ToppingType;
import com.delicious.utils.ToppingCatalog;

import java.util.*;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n".repeat(20));
            System.out.println("========================== Home Screen ==========================");
            System.out.println("\t1) New Order");
            System.out.println("\t0) Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleNewOrder();
                case "0" -> {
                    System.out.println("Exiting application...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void handleNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n".repeat(20));
            System.out.println("========================= Order Menu =========================");
            System.out.println("\t1) Add Sandwich");
            System.out.println("\t2) Add Drink");
            System.out.println("\t3) Add Chips");
            System.out.println("\t4) Checkout");
            System.out.println("\t0) Cancel Order");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> order.addItem(buildSandwich());
                case "2" -> order.addItem(buildDrink());
                case "3" -> order.addItem(buildChip());
                case "4" -> {
                    System.out.println("\n".repeat(20));
                    System.out.println("======================== Order Summary =========================");
                    System.out.println(order.buildOrderSummary());
                    System.out.print("\nPress ENTER to return to the main menu...");
                    scanner.nextLine();
                    ordering = false;
                }
                case "0" -> {
                    System.out.println("Order cancelled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private Sandwich buildSandwich() {
        BreadType bread = promptForBreadType();
        Size size = promptForSandwichSize();

        System.out.println("\n".repeat(20));
        System.out.println("====================== Toasted Option =========================");
        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(size, bread, toasted);
        List<String> addedToppings = new ArrayList<>();

        Map<ToppingType, List<String>> categorized = new LinkedHashMap<>();
        for (ToppingType type : ToppingType.values()) categorized.put(type, new ArrayList<>());

        for (Map.Entry<String, ToppingType> entry : ToppingCatalog.getAllToppings().entrySet()) {
            categorized.get(entry.getValue()).add(entry.getKey());
        }

        int maxRows = categorized.values().stream().mapToInt(List::size).max().orElse(0);
        List<ToppingType> types = new ArrayList<>(categorized.keySet());

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("\n".repeat(20));
            System.out.println("================================== Topping Selection ===================================");
            for (ToppingType type : types) {
                System.out.printf("%-20s", type.name().charAt(0) + type.name().substring(1).toLowerCase());
            }
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------");

            for (int row = 0; row < maxRows; row++) {
                for (ToppingType type : types) {
                    List<String> items = categorized.get(type);
                    String value = row < items.size() ? items.get(row) : "";
                    System.out.printf("%-20s", value);
                }
                System.out.println();
            }
            System.out.println();

            if (!addedToppings.isEmpty()) {
                System.out.println("\nToppings added: " + String.join(", ", addedToppings));
            }

            System.out.print("Enter a topping from the list (or type 'done'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                addingToppings = false;
            } else if (ToppingCatalog.isValidTopping(input)) {
                String normalizedName = ToppingCatalog.getAllToppings()
                        .keySet().stream()
                        .filter(key -> key.equalsIgnoreCase(input))
                        .findFirst()
                        .orElse(input);

                ToppingType type = ToppingCatalog.getToppingType(input);
                System.out.print("Mark this as an extra portion? (yes/no): ");
                boolean isExtra = scanner.nextLine().trim().equalsIgnoreCase("yes");
                sandwich.addTopping(new Topping(normalizedName, type, isExtra));
                addedToppings.add(normalizedName + (isExtra ? " (extra)" : ""));
            } else {
                System.out.println("Invalid topping. Please choose from the list.");
                System.out.print("Press ENTER to continue...");
                scanner.nextLine();
            }
        }

        return sandwich;
    }

    private Drink buildDrink() {
        Size size = promptForDrinkSize();

        System.out.println("\n".repeat(20));
        System.out.println("========================= Drink Entry ==========================");
        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine().trim();

        return new Drink(size, flavor);
    }

    private Chip buildChip() {
        System.out.println("\n".repeat(20));
        System.out.println("========================== Chip Entry ==========================");
        System.out.print("Enter chip type: ");
        String type = scanner.nextLine().trim();

        return new Chip(type);
    }

    private BreadType promptForBreadType() {
        System.out.println("\n".repeat(20));
        System.out.println("======================== Bread Selection ========================");
        BreadType[] options = BreadType.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\t%d) %s\n", i + 1, capitalize(options[i].name()));
        }
        System.out.print("\nEnter your choice: ");

        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < options.length) return options[index];
            } catch (NumberFormatException ignored) {
                for (BreadType type : options) {
                    if (type.name().equalsIgnoreCase(input)) return type;
                }
            }

            System.out.println("Invalid input. Try again.");
        }
    }

    private Size promptForSandwichSize() {
        System.out.println("\n".repeat(20));
        System.out.println("======================= Sandwich Size ==========================");
        System.out.println("\t1) 4\" (four)");
        System.out.println("\t2) 8\" (eight)");
        System.out.println("\t3) 12\" (twelve)");
        System.out.print("\nEnter your choice: ");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1", "4", "four", "4\"" -> {
                    return Size.FOUR_INCH;
                }
                case "2", "8", "eight", "8\"" -> {
                    return Size.EIGHT_INCH;
                }
                case "3", "12", "twelve", "12\"" -> {
                    return Size.TWELVE_INCH;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }

    private Size promptForDrinkSize() {
        System.out.println("\n".repeat(20));
        System.out.println("========================= Drink Size ===========================");
        System.out.println("\t1) Small");
        System.out.println("\t2) Medium");
        System.out.println("\t3) Large");
        System.out.print("\nEnter your choice: ");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1", "small" -> {
                    return Size.SMALL;
                }
                case "2", "medium" -> {
                    return Size.MEDIUM;
                }
                case "3", "large" -> {
                    return Size.LARGE;
                }
                default -> System.out.println("Invalid input. Try again.\n");
            }
        }
    }

    private String capitalize(String text) {
        return text.charAt(0) + text.substring(1).toLowerCase();
    }
}