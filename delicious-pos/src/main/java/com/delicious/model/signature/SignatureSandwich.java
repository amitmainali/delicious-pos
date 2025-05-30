package com.delicious.model.signature;

import com.delicious.model.Sandwich;
import com.delicious.model.Topping;
import com.delicious.model.enums.BreadType;
import com.delicious.model.enums.Size;

import java.util.List;

public class SignatureSandwich extends Sandwich {
    private final String name;

    public SignatureSandwich(String name, Size size, BreadType breadType, boolean toasted, List<Topping> toppings) {
        super(size, breadType, toasted);
        this.name = name;
        setCustomName(name);
        toppings.forEach(this::addTopping);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " Sandwich";
    }
}