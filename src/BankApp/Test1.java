package BankApp;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.loadPersons();
        bank.loadAccounts();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. ����� � �������");
            System.out.println("2. ������������������");
            System.out.println("3. ������� ����������");
            System.out.println("�������� ��������: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.login();
                    break;
                case 2:
                    bank.register();
                    break;
                case 3:
                    System.out.println("�������, ��� ��������������� ����� ����������!");
                    return; // ���������� ������ ����������1
                default:
                    System.out.println("�������� �����.");
            }
        }
    }
}
