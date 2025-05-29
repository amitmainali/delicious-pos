package com.delicious.ui;

import com.delicious.data.ReceiptWriter;
import com.delicious.model.*;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;
import com.delicious.model.signature.SignatureSandwich;
import com.delicious.model.signature.SignatureSandwiches;
import com.delicious.utils.InputUtils;
import com.delicious.utils.MenuUtils;
import com.delicious.utils.ToppingCatalog;

import java.util.*;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            MenuUtils.printHeader("Home Screen");
            System.out.println("\t1) New Order");
            System.out.println("\t0) Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleNewOrder();
                case "0" -> {
                    System.out.println("Exiting application...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void handleNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            MenuUtils.printHeader("Order Menu");
            System.out.println("\t1) Add Sandwich");
            System.out.println("\t2) Add Signature Sandwich");
            System.out.println("\t3) Add Drink");
            System.out.println("\t4) Add Chips");
            System.out.println("\t5) Checkout");
            System.out.println("\t0) Cancel Order");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> order.addItem(buildSandwich());
                case "2" -> order.addItem(selectSignature());
                case "3" -> order.addItem(buildDrink());
                case "4" -> order.addItem(buildChip());
                case "5" -> {
                    MenuUtils.printHeader("Order Summary");
                    System.out.println(order.buildOrderSummary());
                    ReceiptWriter.write(order);

                    System.out.print("\nPress ENTER to return to the main menu...");
                    scanner.nextLine();
                    ordering = false;
                }
                case "0" -> {
                    System.out.println("Order cancelled.");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private Sandwich buildSandwich() {
        BreadType bread = InputUtils.promptBreadType(scanner);
        Size size = InputUtils.promptSandwichSize(scanner);
        boolean toasted = InputUtils.promptYesNo(scanner, "Toasted? (yes/no): ");
        Sandwich sandwich = new Sandwich(size, bread, toasted);
        return InputUtils.customizeSandwich(scanner, sandwich);
    }

    private Sandwich selectSignature() {
        List<SignatureSandwich> list = SignatureSandwiches.getAll();
        MenuUtils.printHeader("Signature Sandwich Menu");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d) %s - $%.2f\n", i + 1, list.get(i).getName(), list.get(i).getPrice());
        }
        System.out.print("\nEnter your choice: ");

        while (true) {
            try {
                int i = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (i >= 0 && i < list.size()) {
                    SignatureSandwich sig = list.get(i);
                    Sandwich copy = new Sandwich(sig.getSize(), sig.getBreadType(), sig.isToasted());
                    copy.setCustomName(sig.getName());
                    sig.getToppings().forEach(copy::addTopping);
                    if (InputUtils.promptYesNo(scanner, "\nWould you like to customize this sandwich? (yes/no): ")) {
                        return InputUtils.customizeSandwich(scanner, copy);
                    }
                    return copy;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Invalid input. Try again: ");
        }
    }

    private Drink buildDrink() {
        Size size = InputUtils.promptDrinkSize(scanner);
        MenuUtils.printHeader("Drink Entry");
        System.out.print("Enter drink flavor: ");
        return new Drink(size, scanner.nextLine().trim());
    }

    private Chip buildChip() {
        MenuUtils.printHeader("Chip Entry");
        System.out.print("Enter chip type: ");
        return new Chip(scanner.nextLine().trim());
    }
}