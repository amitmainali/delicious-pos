package com.delicious.data;

import com.delicious.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final String RECEIPT_FOLDER = "receipts";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static void write(Order order) {
        try {
            File directory = new File(RECEIPT_FOLDER);
            if (!directory.exists()) {
                directory.mkdir();
            }

            String timestamp = LocalDateTime.now().format(FORMATTER);
            File receiptFile = new File(directory, timestamp + ".txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
                writer.write("=========================== RECEIPT ===========================\n");
                writer.write("Timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
                writer.write(order.buildOrderSummary());
                writer.write("\n===============================================================\n");
            }

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}