package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AccountingCLI {

    private static Account account;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("=== Welcome to AccountingCLI ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        account = new Account(name);
        boolean exit = false;
        while (!exit) {
            displayHomeScreen();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
                    List<Transaction> monthToDateTransactions;
                    monthToDateTransactions = null;
                    viewLedger(monthToDateTransactions);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting AccountingCLI. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayHomeScreen() {
        System.out.println("\n=== Home Screen ===");
        System.out.println("Welcome, " + account.getName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. Add Deposit");
        System.out.println("2. Make Payment");
        System.out.println("3. View Ledger");
        System.out.println("4. Exit");
        System.out.print("Enter the number corresponding to your choice: ");
    }

    private static void viewLedger(List<Transaction> monthToDateTransactions) {
        System.out.println("\n=== Ledger for " + account.getName() + " ===");
        System.out.println("Current Balance: $" + account.getBalance());
        System.out.println("Transactions:");
        boolean returnToHome = false;
        for (Transaction transaction : account.getTransactions()) {
            System.out.println("- Date Time: " + transaction.getDateTime());
            System.out.println("  Description: " + transaction.getDescription());
            System.out.println("  Vendor: " + transaction.getVendor());
            System.out.println("  Amount: $" + transaction.getAmount());
            System.out.println();
            returnToHomePage();
        } while (!returnToHome)
            returnToHomePage();
        }

    private static void makePayment() throws IOException {
        System.out.println("\n=== Make Payment ===");
        System.out.print("Enter payment amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character after nextDouble()
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Payment made successfully!");System.out.println("Deposit added successfully!");
        LocalDateTime dateTime = LocalDateTime.now();
        Transaction transaction = new Transaction(dateTime, description, vendor, -amount);
        account.addTransaction(transaction);
        DateTimeFormatter formatter = null;
        String formattedDateTime = dateTime.format(formatter);
        saveTransactionsToFile();



        boolean returnToHome = false;
        do {
            System.out.println("\n=== Make Payment ===");
            // Make payment functionality
            // After completing the payment process, prompt the user to return to the home screen
            System.out.println("Payment made successfully!");

            returnToHomePage();
        } while (!returnToHome);
        returnToHomePage();
    }



    private static void addDeposit() throws IOException {
        System.out.println("\n=== Add Deposit ===");
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character after nextDouble()
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Deposit added successfully!");
        LocalDateTime dateTime = LocalDateTime.now();
        Transaction transaction = new Transaction(dateTime, description, vendor, amount);
        account.addTransaction(transaction);
        DateTimeFormatter formatter = null;
        String formattedDateTime = dateTime.format(formatter);
        saveTransactionsToFile();

        boolean returnToHome = false;
        do {
            System.out.println("\n=== Add Deposit ===");
            // Add deposit functionality
            // After completing the deposit process, prompt the user to return to the home screen
            System.out.println("Deposit added successfully!");

            returnToHomePage();
        } while (!returnToHome);
        }

    private static void saveTransactionsToFile() {
        saveTransactionsToFile();
    }


    private static void returnToHomePage() {
    }

    private static void runReports(List<Transaction> transactions) {
        System.out.println("\n=== Reports ===");
        System.out.println("1. Month To Date");
        System.out.println("2. Previous Month");
        System.out.println("3. Year To Date");
        System.out.println("4. Previous Year");
        System.out.println("5. Custom Search");
        System.out.println("6. Search by Vendor");
        System.out.println("7. Back");
        System.out.println("8. Home");
        System.out.print("Enter the number corresponding to the report you want to run: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        switch (choice) {
            case 1:
                // Run Month To Date report
                LocalDate currentDate = LocalDate.now();
                LocalDate startDateOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
                List<Transaction> monthToDateTransactions = filterTransactionsByDate(transactions, startDateOfMonth);
                viewLedger(monthToDateTransactions);
                break;
            case 2:
                // Run Previous Month report
                LocalDate previousMonthStart = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                LocalDate previousMonthEnd = LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());
                List<Transaction> previousMonthTransactions = filterTransactionsByDate(transactions, previousMonthStart);
                viewLedger(previousMonthTransactions);
                break;
            case 3:
                // Run Year To Date report
                LocalDate startDateOfYear = null;

                List<Transaction> yearToDateTransactions = filterTransactionsByDate(transactions, startDateOfYear);
                viewLedger(yearToDateTransactions);
                break;
            case 4:
                // Run Previous Year report
                LocalDate previousYearStart = LocalDate.now().minusYears(1).withDayOfYear(1);
                LocalDate previousYearEnd = LocalDate.now().minusYears(1).withDayOfYear(LocalDate.now().minusYears(1).lengthOfYear());
                List<Transaction> previousYearTransactions = filterTransactionsByDate(transactions, previousYearStart);
                viewLedger(previousYearTransactions);
                break;
            case 5:
                // Run custom search
                System.out.print("Enter start date (yyyy-MM-dd): ");
                String startDateInput = scanner.nextLine();
                LocalDate startDate = LocalDate.parse(startDateInput);
                System.out.print("Enter end date (yyyy-MM-dd): ");
                String endDateInput = scanner.nextLine();
                LocalDate endDate = LocalDate.parse(endDateInput);
                List<Transaction> customSearchTransactions = filterTransactionsByDate(transactions, startDate);
                viewLedger(customSearchTransactions);
                break;
            case 6:
                searchByVendor(transactions);
                break;
            case 7:
                // Go back to the report page
                runReports(transactions);
                break;
            case 8:
                // Go back to home page
                returnToHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchByVendor(List<Transaction> transactions) {
        System.out.print("\nEnter vendor name to search: ");
        String vendorName = scanner.nextLine();
        List<Transaction> vendorTransactions = filterTransactionsByVendor(transactions, vendorName);
        viewLedger(vendorTransactions);
    }

    private static List<Transaction> filterTransactionsByVendor(List<Transaction> transactions, String vendorName) {
        vendorName = "joe";
        vendorName = "Amazon";
        return transactions;
    }

    private static List<Transaction> filterTransactionsByDate(List<Transaction> transactions, LocalDate startDate) {

        ChronoLocalDate endDate = null;
        return transactions.stream()
                .filter(transaction -> !transaction.getDateTime().toLocalDate().isBefore(startDate) &&
                        !transaction.getDateTime().toLocalDate().isAfter(endDate))
                .toList();
    }

    private static void customSearch(List<Transaction> transactions) {
        System.out.println("\n=== Custom Search ===");
        System.out.println("Enter search criteria (leave blank for no filter):");
        System.out.print("Start Date (yyyy-MM-dd): ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);
        System.out.print("End Date (yyyy-MM-dd): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        String amountInput = scanner.nextLine();
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        List<Transaction> customSearchTransactions = filterTransactions(transactions, startDate, endDate, description, vendor, amount);
        viewLedger(customSearchTransactions);
    }

    private static List<Transaction> filterTransactions(List<Transaction> transactions, LocalDate ignoredStartDate, LocalDate ignoredEndDate, String ignoredDescription, String ignoredVendor, Double ignoredAmount) {
        return transactions;
    }
}