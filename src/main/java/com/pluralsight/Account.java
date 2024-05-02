package com.pluralsight;

import java.util.List;

public class Account {
    private String name;
    private double balance;
    List<Transaction> transactions;


    public Account(String name, double balance, List<Transaction> transactions) {
        this.name = name;
        this.balance = 0;
        this.transactions = transactions;
    }

    public Account(String name) {
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

}



