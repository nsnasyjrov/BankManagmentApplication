package BankApp;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.loadPersons();
        bank.loadAccounts();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Войти в профиль");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Закрыть приложение");
            System.out.println("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.login();
                    break;
                case 2:
                    bank.register();
                    break;
                case 3:
                    System.out.println("Спасибо, что воспользовались нашим терминалом!");
                    return; // Завершение работы приложения1
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
