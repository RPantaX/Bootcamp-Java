package org.example.accounts.accountsImpl;

import org.example.accounts.Account;
import java.lang.*;
public class CurrentAccount implements Account {
    private double capital;
    private static final double limiteDeCredito=500;
    private static final double interes=00.7;

    public CurrentAccount(double capital) {
        this.capital = capital;
    }

    @Override
    public void deposit(double amount) {
        capital+=amount;
        System.out.println("usted depositó s/"+amount+" a la cuenta corriente");

    }

    @Override
    public void withdraw(double amount) {
        if (amount+capital >= limiteDeCredito) {
            capital -= amount;
            System.out.println("usted retiró s/"+amount+" de la cuenta corriente");
        } else {
            System.out.println("s/"+amount+" Excede el límite de sobregiro.");
        }
    }

    @Override
    public double calculateInterest() {
        return capital*0.4;
    }

    public double getCapital() {
        return capital;
    }
}
