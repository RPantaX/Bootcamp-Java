package org.example.entities;
import java.lang.*;
public class Person {
    private String accountNumber;
    private Long DNI;
    private String name;
    private String lastName;

    public Person() {
    }

    public Person(String accountNumber, Long DNI, String name, String lastName) {
        this.accountNumber = accountNumber;
        this.DNI = DNI;
        this.name = name;
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", DNI=" + DNI +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
