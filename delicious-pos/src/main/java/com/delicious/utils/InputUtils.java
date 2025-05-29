package com.delicious.utils;

import com.delicious.model.Sandwich;
import com.delicious.model.Topping;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;
import com.delicious.model.enums.ToppingType;

import java.util.*;

public class InputUtils {

    public static BreadType promptBreadType(Scanner scanner) {
        MenuUtils.printHeader("Bread Selection");
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
            System.out.print("Invalid input. Try again: ");
        }
    }

    public static Size promptSandwichSize(Scanner scanner) {
        MenuUtils.printHeader("Sandwich Size");
        System.out.println("\t1) 4\" (four)");
        System.out.println("\t2) 8\" (eight)");
        System.out.println("\t3) 12\" (twelve)");
        System.out.print("\nEnter your choice: ");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "4", "four", "4\"" -> { return Size.FOUR_INCH; }
                case "2", "8", "eight", "8\"" -> { return Size.EIGHT_INCH; }
                case "3", "12", "twelve", "12\"" -> { return Size.TWELVE_INCH; }
                default -> System.out.print("Invalid input. Try again: ");
            }
        }
    }

    public static Size promptDrinkSize(Scanner scanner) {
        MenuUtils.printHeader("Drink Size");
        System.out.println("\t1) Small");
        System.out.println("\t2) Medium");
        System.out.println("\t3) Large");
        System.out.print("\nEnter your choice: ");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "small" -> { return Size.SMALL; }
                case "2", "medium" -> { return Size.MEDIUM; }
                case "3", "large" -> { return Size.LARGE; }
                default -> System.out.print("Invalid input. Try again: ");
            }
        }
    }

    public static boolean promptYesNo(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    public static Sandwich customizeSandwich(Scanner scanner, Sandwich sandwich) {
        List<String> added = new ArrayList<>();
        for (Topping t : sandwich.getToppings()) {
            added.add(t.getName() + (t.isExtra() ? " (extra)" : ""));
        }

        Map<ToppingType, List<String>> categorized = new LinkedHashMap<>();
        for (ToppingType type : ToppingType.values()) categorized.put(type, new ArrayList<>());
        for (Map.Entry<String, ToppingType> entry : ToppingCatalog.getAllToppings().entrySet()) {
            categorized.get(entry.getValue()).add(entry.getKey());
        }

        int maxRows = categorized.values().stream().mapToInt(List::size).max().orElse(0);
        List<ToppingType> types = new ArrayList<>(categorized.keySet());

        while (true) {
            System.out.println("\n".repeat(20));
            MenuUtils.printHeader("Customize Your Sandwich");
            for (ToppingType type : types) {
                System.out.printf("%-20s", capitalize(type.name()));
            }
            System.out.println();
            System.out.println("-".repeat(86));

            for (int row = 0; row < maxRows; row++) {
                for (ToppingType type : types) {
                    List<String> items = categorized.get(type);
                    String value = row < items.size() ? items.get(row) : "";
                    System.out.printf("%-20s", value);
                }
                System.out.println();
            }

            if (!added.isEmpty()) {
                System.out.println("\nToppings added: " + String.join(", ", added));
            }

            System.out.println();
            System.out.print("Enter a topping to add (or 'done'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;

            if (ToppingCatalog.isValidTopping(input)) {
                String norm = ToppingCatalog.getNormalizedTopping(input);
                ToppingType type = ToppingCatalog.getToppingType(norm);
                boolean isExtra = promptYesNo(scanner, "Mark this as an extra portion? (yes/no): ");
                sandwich.addTopping(new Topping(norm, type, isExtra));
                added.add(norm + (isExtra ? " (extra)" : ""));
            } else {
                System.out.println("Invalid topping. Please choose from the list.");
                System.out.print("Press ENTER to continue...");
                scanner.nextLine();
            }
        }

        return sandwich;
    }

    private static String capitalize(String word) {
        return word.charAt(0) + word.substring(1).toLowerCase();
    }
}