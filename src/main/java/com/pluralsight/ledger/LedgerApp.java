package com.pluralsight.ledger;

import java.io.BufferedWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {

    private List<Transaction> transactions = new ArrayList<>();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Accounting Ledger ---");
            System.out.println("1) Add Deposit");
            System.out.println("2) Add Payment");
            System.out.println("3) View Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1" -> System.out.println("Deposit feature coming soon!");
                case "2" -> System.out.println("Payment feature coming soon!");
                case "3" -> System.out.println("View ledger coming soon!");
                case "x" -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }
}