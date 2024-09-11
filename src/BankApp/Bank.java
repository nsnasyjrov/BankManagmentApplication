package BankApp;

import  java.io.*;
import java.util.*;

// Объявление класса
public class Bank {

    // Список пользователей (клиентов банка)
    private List<Person> persons = new ArrayList<>();

    // Словарь для хранения банковских счетов, где ключ - логин пользователя, значение - объект BankAccount
    private Map<String, BankAccount> accounts = new HashMap<>();

    // Метод для загрузки данных пользователей из файла
    public void loadPersons() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persons.dat"))) {
            // Чтение списка пользователей из файла
            persons = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось загрузить данные...");
        }
    }

    // Метод сохранения данных пользователей в файл
    public void savePersons() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persons.dat"))) {
            // Запись пользователей в файл
            oos.writeObject(persons);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить...");
        }
    }
    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить данные о счетах...");
        }
    }
    public void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось загрузить данные о счетах...");
        }
    }

    // Метод регистрации нового пользователя
    public void register() {
        Scanner scan = new Scanner(System.in);

        // Запрос данных пользователя

        System.out.print("Введите имя: ");
        String name = scan.nextLine();


        System.out.print("Введите фамилию: ");
        String surname = scan.nextLine();


        System.out.print("Введите возраст: ");
        int age = scan.nextInt();
        scan.nextLine(); // счет оставшегося символа новой строки


        System.out.print("Введите логин: ");
        String login = scan.nextLine();


        System.out.print("Введите пароль: ");
        String password = scan.nextLine();


        // Создание нового пользователя и добавление его в список

        Person person = new Person(name, surname, age, login, password);
        persons.add(person);

        // Генерация уникального номера счета
        String accountNumber = UUID.randomUUID().toString();

        // Создаем банковский аккаунт и связываем его с логином пользователя
        BankAccount account = new BankAccount(accountNumber, name);
        accounts.put(login, account);

        // Сохранение данных пользователя в файл
        savePersons();
        saveAccounts();

        // Сообщение об успешной регистрации:
        System.out.println("Регистрация успешно завершена! Ваш номер счета: " + accountNumber);
    }

    // Метод для входа пользователя в систему:
    public void login() {
        Scanner scan = new Scanner(System.in);

        // Запрос логина и пароля
        System.out.print("Введите логин: ");
        String login = scan.nextLine();

        System.out.println("Введите пароль: ");
        String password = scan.nextLine();

        // Проверка пользователя - есть ли пользователь с таким логином и паролем
        for (Person person : persons) {
            if (person.getLogin().equals(login) && person.getPassword().equals(password)) {
                System.out.println("Добро пожаловать в банковское приложение, " + person.getName() + " " + person.getSurname() + "!");
                // Переходим в меню взаимодействия после успешного входа
                helloMessageMethod(login);
                return;
            }
        }

        // Если не удалось найти пользователя с такими данными:
        System.out.println("Неверный логин или пароль. Попробуйте еще раз.");
    }

    // Метод вызова меню взаимодействия
    public void helloMessageMethod(String login) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            // Отображение доступного функционала
            System.out.println("Добро пожаловать в банковское приложение!");
            System.out.println("Чтобы воспользоваться приложением введите определенную цифру: ");
            System.out.println("1 - пополнить баланс счета...1");
            System.out.println("2 - снять деньги со счета...");
            System.out.println("3 - посмотреть текущий баланс...");
            System.out.println("4 - удаление банковского счета...");
            System.out.println("5 - закрыть приложение и завершить работу с терминалом...");

            int choice = scan.nextInt();

            switch(choice) {
                // пополнение средств
                case 1:
                    System.out.print("Введите сумму для пополнения: ");
                    double depositAmount = scan.nextDouble();
                    accounts.get(login).deposit(depositAmount);
                    System.out.println("Счет пополнен на " + depositAmount + " рублей.");
                    break;
                    // Снятие средств со счета
                case 2:
                    System.out.print("Введите сумму, которую хотите снять:");
                    double withdrawAmount = scan.nextDouble();
                    accounts.get(login).withdraw(withdrawAmount);
                    break;
                // Посмотреть текущий баланс:
                case 3:
                    System.out.println("Ваш текущий баланс составляет: " + accounts.get(login).getBalance() + " рублей.");
                    break;
                    // удаление банковского счета
                case 4:
                    deleteAccount(login);
                    return;

                case 5:
                    saveAccounts();
                    return;
                default:
                    System.out.println("Введено неправильное значение");
            }
        }
    }

    // метод удаления аккаунта.
    public void deleteAccount(String login) {
        persons.removeIf(person -> person.getLogin().equals(login));
        accounts.remove(login);
        savePersons();
        saveAccounts();
        System.out.println("Ваш банковский профиль удален!");
    }
}



















