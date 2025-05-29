package com.delicious.model.signature;

import com.delicious.model.Topping;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;
import com.delicious.model.enums.ToppingType;

import java.util.Arrays;
import java.util.List;

public class SignatureSandwiches {
    public static List<SignatureSandwich> getAll() {
        return List.of(
                new SignatureSandwich("Spicy Italian", Size.TWELVE_INCH, BreadType.WHITE, true,
                        Arrays.asList(
                                new Topping("Pepperoni", ToppingType.MEAT, false),
                                new Topping("Salami", ToppingType.MEAT, false),
                                new Topping("Banana Peppers", ToppingType.REGULAR, false),
                                new Topping("Jalapenos", ToppingType.REGULAR, false),
                                new Topping("Chipotle Mayo", ToppingType.SAUCE, false)
                        )),
                new SignatureSandwich("Veggie Delight", Size.EIGHT_INCH, BreadType.WHEAT, false,
                        Arrays.asList(
                                new Topping("Lettuce", ToppingType.REGULAR, false),
                                new Topping("Tomato", ToppingType.REGULAR, false),
                                new Topping("Cucumber", ToppingType.REGULAR, false),
                                new Topping("Onion", ToppingType.REGULAR, false),
                                new Topping("Swiss", ToppingType.CHEESE, false)
                        )),
                new SignatureSandwich("Turkey Bacon Club", Size.TWELVE_INCH, BreadType.WHITE, true,
                        Arrays.asList(
                                new Topping("Turkey", ToppingType.MEAT, false),
                                new Topping("Bacon", ToppingType.MEAT, false),
                                new Topping("Lettuce", ToppingType.REGULAR, false),
                                new Topping("Tomato", ToppingType.REGULAR, false),
                                new Topping("Cheddar", ToppingType.CHEESE, false)
                        )),
                new SignatureSandwich("Chicken Ranch Melt", Size.EIGHT_INCH, BreadType.WHEAT, true,
                        Arrays.asList(
                                new Topping("Chicken", ToppingType.MEAT, false),
                                new Topping("Ranch", ToppingType.SAUCE, false),
                                new Topping("Lettuce", ToppingType.REGULAR, false),
                                new Topping("Bacon", ToppingType.MEAT, false),
                                new Topping("Mozzarella", ToppingType.CHEESE, false)
                        )),
                new SignatureSandwich("Ham & Swiss", Size.FOUR_INCH, BreadType.WHITE, false,
                        Arrays.asList(
                                new Topping("Ham", ToppingType.MEAT, false),
                                new Topping("Swiss", ToppingType.CHEESE, false),
                                new Topping("Mustard", ToppingType.SAUCE, false)
                        )),
                new SignatureSandwich("Steak & Cheese", Size.TWELVE_INCH, BreadType.WHEAT, true,
                        Arrays.asList(
                                new Topping("Steak", ToppingType.MEAT, false),
                                new Topping("Green Peppers", ToppingType.REGULAR, false),
                                new Topping("Onion", ToppingType.REGULAR, false),
                                new Topping("American", ToppingType.CHEESE, false)
                        )),
                new SignatureSandwich("Tuna Classic", Size.EIGHT_INCH, BreadType.WHITE, false,
                        Arrays.asList(
                                new Topping("Tuna", ToppingType.MEAT, false),
                                new Topping("Lettuce", ToppingType.REGULAR, false),
                                new Topping("Tomato", ToppingType.REGULAR, false),
                                new Topping("Mayo", ToppingType.SAUCE, false)
                        ))
        );
    }
}