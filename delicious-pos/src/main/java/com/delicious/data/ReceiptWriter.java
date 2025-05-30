package com.delicious.data;

import com.delicious.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final String RECEIPT_FILE = "receipt_file.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void write(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEIPT_FILE, true))) {
            writer.write("=========================== RECEIPT ===========================\n");
            writer.write("Timestamp: " + LocalDateTime.now().format(FORMATTER) + "\n");
            writer.write(order.buildOrderSummary());
            writer.write("\n===============================================================\n\n");
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}