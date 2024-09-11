package BankApp;

import java.util.Scanner;

public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;


    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0; // ��������� ������
    }


    // ������

    //     ����� ����� ������� �� ����
    public void deposit(double amount) {
        this.balance += amount;
    }

    //
    // ����� ������ ������� �� �����
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("������������ �������!");
        }
    }

    //
    public double getBalance() {
        return balance;
    }

    // getters and setters:

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}