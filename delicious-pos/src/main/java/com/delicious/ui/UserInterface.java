package com.delicious.ui;

import com.delicious.model.*;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("* DELI-cious POS *");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");
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
            System.out.println("* Order Menu *");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> order.addItem(buildSandwich());
                case "2" -> order.addItem(buildDrink());
                case "3" -> order.addItem(buildChip());
                case "4" -> {
                    System.out.println("\n**** Order Summary ****");
                    System.out.println(order.buildOrderSummary());
                    System.out.println("Order completed.\n");
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
        System.out.println("Select bread type: WHITE, WHEAT, RYE, WRAP");
        BreadType bread = BreadType.valueOf(scanner.nextLine().trim().toUpperCase());

        System.out.println("Select sandwich size: FOUR_INCH, EIGHT_INCH, TWELVE_INCH");
        Size size = Size.valueOf(scanner.nextLine().trim().toUpperCase());

        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(size, bread, toasted);

        System.out.println("Enter meats (comma-separated or leave blank): ");
        String meats = scanner.nextLine().trim();
        for (String meat : meats.split(",")) {
            if (!meat.isBlank()) sandwich.addMeat(meat.trim());
        }

        System.out.println("Enter cheeses (comma-separated or leave blank): ");
        String cheeses = scanner.nextLine().trim();
        for (String cheese : cheeses.split(",")) {
            if (!cheese.isBlank()) sandwich.addCheese(cheese.trim());
        }

        System.out.println("Enter regular toppings (comma-separated or leave blank): ");
        String toppings = scanner.nextLine().trim();
        for (String topping : toppings.split(",")) {
            if (!topping.isBlank()) sandwich.addRegularTopping(topping.trim());
        }

        System.out.println("Enter sauces (comma-separated or leave blank): ");
        String sauces = scanner.nextLine().trim();
        for (String sauce : sauces.split(",")) {
            if (!sauce.isBlank()) sandwich.addSauce(sauce.trim());
        }

        System.out.println("Enter sides (comma-separated or leave blank): ");
        String sides = scanner.nextLine().trim();
        for (String side : sides.split(",")) {
            if (!side.isBlank()) sandwich.addSide(side.trim());
        }

        return sandwich;
    }

    private Drink buildDrink() {
        System.out.println("Select drink size: SMALL, MEDIUM, LARGE");
        Size size = Size.valueOf(scanner.nextLine().trim().toUpperCase());

        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine().trim();

        return new Drink(size, flavor);
    }

    private Chip buildChip() {
        System.out.print("Enter chip type: ");
        String type = scanner.nextLine().trim();

        return new Chip(type);
    }
}