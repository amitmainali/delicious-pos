package com.delicious.io;

import com.delicious.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    private static final String RECEIPT_FILE = "receipt_file.txt";

    public static void writeReceipt(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEIPT_FILE, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);

            writer.write("==================== RECEIPT ====================\n");
            writer.write("Timestamp: " + timestamp + "\n");
            writer.write(order.buildOrderSummary());
            writer.write("=================================================\n\n");

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}