package org.example;

import com.sun.security.jgss.GSSUtil;
import org.example.accounts.Account;
import org.example.accounts.accountsImpl.CurrentAccount;
import org.example.accounts.accountsImpl.SavingsAccount;
import org.example.banks.Bank;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        Bank bank= new Bank();
        SavingsAccount savingsAccount = new SavingsAccount(1000);
        CurrentAccount currentAccount = new CurrentAccount(500);
        // Realiza operaciones

        bank.addAccount(savingsAccount);
        bank.addAccount(currentAccount);
        List<Account> accounts= bank.getAccounts();
        System.out.println("\n                    -------------------BIENVENIDOS AL BANCO---------------------------\n");

        System.out.println("Saldo inicial de la cuenta de ahorros: " + savingsAccount.getCapital());
        System.out.println("Saldo inciial de la cuenta corriente: " + currentAccount.getCapital());
        System.out.println("-----------------------------------------------------------");
        System.out.println("\t\t\t\t Movimientos");
        for (Account acc : accounts) {
            acc.deposit(200);
            acc.withdraw(300);

        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Saldo final de la cuenta de ahorros: " + savingsAccount.getCapital());
        System.out.println("Saldo final de la cuenta corriente: " + currentAccount.getCapital());
    }
}