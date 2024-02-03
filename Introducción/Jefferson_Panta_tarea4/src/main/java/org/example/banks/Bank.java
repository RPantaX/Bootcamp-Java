package org.example.banks;
import org.example.accounts.Account;

import java.util.ArrayList;
import java.util.List;
public class Bank {
    private List<Account> accounts = new ArrayList<>(); //almacenar una lista de cuentas
    public Bank() {
    }

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(Account account){ //Método para agregar cuentas a la lista del banco.
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
