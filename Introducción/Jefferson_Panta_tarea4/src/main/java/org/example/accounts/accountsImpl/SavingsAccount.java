package org.example.accounts.accountsImpl;
import java.lang.*;
import org.example.accounts.Account;

public class SavingsAccount implements Account {
    private double capital;
    private static final double interes=00.4;

    public SavingsAccount(double capital) {
        this.capital = capital;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("usted depositó s/"+amount+" de la cuenta de ahorros");

        capital+=amount;
    }

    @Override
    public void withdraw(double amount) {
        if (capital >= amount) {
            capital -= amount;
            System.out.println("usted retiró s/"+amount+" de la cuenta de ahorros");
        } else {
            System.out.println("Saldo insuficiente. Usted tiene ahorrado: s/"+capital);
        }
    }

    @Override
    public double calculateInterest() {
        return capital*0.4;
    }
    double calculateInterestRate(){
        return interes*100;
    }

    public double getCapital() {
        return capital;
    }
}
