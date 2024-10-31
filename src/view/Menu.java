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

}
