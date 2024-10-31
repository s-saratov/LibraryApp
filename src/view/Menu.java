package view;

import service.MainService;

import java.util.Scanner;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    private void waitRead() {
        System.out.print("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    // Возвращает только введённое число (предотвращая ошибку в случае ввода символов)
    private int getSelection() {
        int selection;
        while (true) {
            System.out.printf("Введите Ваш выбор: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Вы ввели не число. Повторите ввод.");
                scanner.nextLine();
            } else {
                selection = scanner.nextInt();
                scanner.nextLine();
                return selection;
            }
        }
    }

    // Главное меню
    private void showMainMenu() {
        System.out.println("Добро пожаловать в библиотеку 'Знания века'!!");
        System.out.println("1. Меню книг -> showBookMenu()");
        System.out.println("2. Меню пользователя -> showUserMenu()");
        System.out.println("3. Меню администратора -> showAdminMenu()");
        System.out.println("0. Выход из системы");

        switch (getSelection()) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                showAdminMenu();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный выбор. Повторите ввод.");
                showMainMenu();
                break;
        }
    }

    private void showBookMenu() {
        System.out.println("Здесь будет меню книг.");
        waitRead();
    }

    private void showUserMenu() {
        System.out.println("Здесь будет меню пользователя.");
        waitRead();
    }

    private void showAdminMenu() {
        System.out.println("Здесь будет меню администратора.");
        waitRead();
    }
}