package com.pluralsight;

import java.util.List;

public class AccountImpl extends Account {
    private static String name;
    private static String Amazon;
    private static double Balance;
    private static List<Transaction> transactions;


    public AccountImpl() {
        super(name, Balance, transactions);
    }
}
