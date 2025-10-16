package com.pluralsight.ledger;

import java.time.LocalDate;
import java.util.Scanner;

public class LedgerApp {
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

    private void addDeposit(Scanner scanner) {
        System.out.println("\n--- Add Deposit ---");
        System.out.println("Enter description: ");
        String description = scanner.nextLine();

        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction payment = new Transaction(LocalDate.now(), description, vendor, amount);
        transactions.add(payment);

        System.out.println("Deposit added successfully!");
    }

    private void addPayment(Scanner scanner) {

    }
}