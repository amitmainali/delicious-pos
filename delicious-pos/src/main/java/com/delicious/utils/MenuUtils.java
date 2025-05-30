package com.delicious.utils;

public class MenuUtils {
    private static final int WIDTH = 65;

    public static void printHeader(String title) {
        System.out.println("\n".repeat(20));
        System.out.println("\n" + "=".repeat(WIDTH));
        System.out.printf("%s%s%s\n",
                " ".repeat((WIDTH - title.length()) / 2),
                title,
                " ".repeat((WIDTH - title.length() + 1) / 2));
        System.out.println("=".repeat(WIDTH));
    }
}