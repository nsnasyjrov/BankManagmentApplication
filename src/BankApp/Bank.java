package BankApp;

import  java.io.*;
import java.util.*;

// ���������� ������
public class Bank {

    // ������ ������������� (�������� �����)
    private List<Person> persons = new ArrayList<>();

    // ������� ��� �������� ���������� ������, ��� ���� - ����� ������������, �������� - ������ BankAccount
    private Map<String, BankAccount> accounts = new HashMap<>();

    // ����� ��� �������� ������ ������������� �� �����
    public void loadPersons() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persons.dat"))) {
            // ������ ������ ������������� �� �����
            persons = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("�� ������� ��������� ������...");
        }
    }

    // ����� ���������� ������ ������������� � ����
    public void savePersons() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persons.dat"))) {
            // ������ ������������� � ����
            oos.writeObject(persons);
        } catch (IOException e) {
            System.out.println("�� ������� ���������...");
        }
    }
    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("�� ������� ��������� ������ � ������...");
        }
    }
    public void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("�� ������� ��������� ������ � ������...");
        }
    }

    // ����� ����������� ������ ������������
    public void register() {
        Scanner scan = new Scanner(System.in);

        // ������ ������ ������������

        System.out.print("������� ���: ");
        String name = scan.nextLine();


        System.out.print("������� �������: ");
        String surname = scan.nextLine();


        System.out.print("������� �������: ");
        int age = scan.nextInt();
        scan.nextLine(); // ���� ����������� ������� ����� ������


        System.out.print("������� �����: ");
        String login = scan.nextLine();


        System.out.print("������� ������: ");
        String password = scan.nextLine();


        // �������� ������ ������������ � ���������� ��� � ������

        Person person = new Person(name, surname, age, login, password);
        persons.add(person);

        // ��������� ����������� ������ �����
        String accountNumber = UUID.randomUUID().toString();

        // ������� ���������� ������� � ��������� ��� � ������� ������������
        BankAccount account = new BankAccount(accountNumber, name);
        accounts.put(login, account);

        // ���������� ������ ������������ � ����
        savePersons();
        saveAccounts();

        // ��������� �� �������� �����������:
        System.out.println("����������� ������� ���������! ��� ����� �����: " + accountNumber);
    }

    // ����� ��� ����� ������������ � �������:
    public void login() {
        Scanner scan = new Scanner(System.in);

        // ������ ������ � ������
        System.out.print("������� �����: ");
        String login = scan.nextLine();

        System.out.println("������� ������: ");
        String password = scan.nextLine();

        // �������� ������������ - ���� �� ������������ � ����� ������� � �������
        for (Person person : persons) {
            if (person.getLogin().equals(login) && person.getPassword().equals(password)) {
                System.out.println("����� ���������� � ���������� ����������, " + person.getName() + " " + person.getSurname() + "!");
                // ��������� � ���� �������������� ����� ��������� �����
                helloMessageMethod(login);
                return;
            }
        }

        // ���� �� ������� ����� ������������ � ������ �������:
        System.out.println("�������� ����� ��� ������. ���������� ��� ���.");
    }

    // ����� ������ ���� ��������������
    public void helloMessageMethod(String login) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            // ����������� ���������� �����������
            System.out.println("����� ���������� � ���������� ����������!");
            System.out.println("����� ��������������� ����������� ������� ������������ �����: ");
            System.out.println("1 - ��������� ������ �����...1");
            System.out.println("2 - ����� ������ �� �����...");
            System.out.println("3 - ���������� ������� ������...");
            System.out.println("4 - �������� ����������� �����...");
            System.out.println("5 - ������� ���������� � ��������� ������ � ����������...");

            int choice = scan.nextInt();

            switch(choice) {
                // ���������� �������
                case 1:
                    System.out.print("������� ����� ��� ����������: ");
                    double depositAmount = scan.nextDouble();
                    accounts.get(login).deposit(depositAmount);
                    System.out.println("���� �������� �� " + depositAmount + " ������.");
                    break;
                    // ������ ������� �� �����
                case 2:
                    System.out.print("������� �����, ������� ������ �����:");
                    double withdrawAmount = scan.nextDouble();
                    accounts.get(login).withdraw(withdrawAmount);
                    break;
                // ���������� ������� ������:
                case 3:
                    System.out.println("��� ������� ������ ����������: " + accounts.get(login).getBalance() + " ������.");
                    break;
                    // �������� ����������� �����
                case 4:
                    deleteAccount(login);
                    return;

                case 5:
                    saveAccounts();
                    return;
                default:
                    System.out.println("������� ������������ ��������");
            }
        }
    }

    // ����� �������� ��������.
    public void deleteAccount(String login) {
        persons.removeIf(person -> person.getLogin().equals(login));
        accounts.remove(login);
        savePersons();
        saveAccounts();
        System.out.println("��� ���������� ������� ������!");
    }
}



















