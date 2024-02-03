package org.example.accounts;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double calculateInterest();
}
