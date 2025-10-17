package com.pluralsight.ledger;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {

    private final List<Transaction> transactions = new ArrayList<>();

    public void run() {
        loadTransactions();
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

        saveTransactions();

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
        System.out.println("────────────────────────────── ⋆⋅☆⋅⋆ ──────────────────────────────");

        double total = 0;

        for (Transaction t : transactions) {
            System.out.printf("%-12s %-20s %-15s %10.2f%n",
                    t.getDate(), t.getDescription(), t.getVendor(), t.getAmount());
            total += t.getAmount();
        }

        System.out.println("────────────────────────────── ⋆⋅☆⋅⋆ ──────────────────────────────");
        System.out.printf("Current Balance: $%.2f%n", total);
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String description = parts[1];
                    String vendor = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    transactions.add(new Transaction(date, description, vendor, amount));
                }
            }
            System.out.println("Transactions loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved transactions found (a new file will be created).");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"))) {
            for (Transaction t : transactions) {
                writer.write(t.getDate() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                writer.newLine();
            }
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}
