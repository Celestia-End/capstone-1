package com.pluralsight.ledger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {

    private final List<Transaction> transactions = new ArrayList<>();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Accounting Ledger ---");
            System.out.println("1) Add Deposit");
            System.out.println("2) Add Payment");
            System.out.println("3) View Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1" -> addDeposit(scanner);
                case "2" -> addPayment(scanner);
                case "3" -> viewLedger();
                case "X" -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private void addPayment(Scanner scanner) {
        System.out.println("\n--- Add Payment ---");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        amount = -Math.abs(amount);

        Transaction payment = new Transaction(LocalDate.now(), description, vendor, amount);
        transactions.add(payment);

        System.out.println("Payment recorded successfully!");
    }

    private void addDeposit(Scanner scanner) {
        System.out.println("\n--- Add Deposit ---");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction deposit = new Transaction(LocalDate.now(), description, vendor, amount);
        transactions.add(deposit);

        System.out.println("Deposit added successfully!");
    }

    private void viewLedger() {
        System.out.println("\n--- Ledger ---");
        System.out.printf("%-12s %-20s %-15s %10s%n", "Date", "Description", "Vendor", "Amount");
        System.out.println("---------------------------------------------------------------");

        double total = 0;

        for (Transaction t : transactions) {
            System.out.printf("%-12s %-20s %-15s %10.2f%n",
                    t.getDate(), t.getDescription(), t.getVendor(), t.getAmount());
            total += t.getAmount();
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Current Balance: $%.2f%n", total);
    }
}