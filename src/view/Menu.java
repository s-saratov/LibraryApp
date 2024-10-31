package view;

import model.Role;
import service.MainService;

import java.util.Scanner;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    private void showAdminMenu() {
        if (service.getActiveUser().getRole() != Role.ADMIN) {
            System.out.print("Доступ запрещён. Вы не являетесь администратором системы.");
            waitRead();
            showMainMenu();
        }
        System.out.println("\nМеню администратора");
        System.out.println("1. Заблокировать пользователя");
        System.out.println("2. Разблокировать пользователя");
        System.out.println("3. Добавить новую книгу");
        System.out.println("4. Изменить дату возврата книги");
        System.out.println("0. Вернуться в предыдущее меню");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    showMainMenu();
                    break;
                case 1:
                    System.out.println("\nУкажите ID пользователя для блокировки:");
                    System.out.println(service.getUsersByRole(Role.ADMIN, Role.USER).toString());
                    System.out.print("Введите ID блокируемого пользователя: ");
                    int id = getSelection();
                    while (!service.getUserByID(id)) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    service.service.getUserByID(id).setRole(Role.BLOCKED);
                    System.out.printf("\nПользователь id #%d %s заблокирован.", id, service.getUserByID(id).getEmail());
                    waitRead();
                    showAdminMenu();
                    break;
                case 2:
                    System.out.println("\nВыберете пользователя для разблокирования:");
                    System.out.println(service.getUsersByRole(Role.BLOCKED).toString());

                    System.out.print("Введите электронную почту разблокируемого пользователя: ");
                    email = scanner.nextLine();

                    while (service.getUserByEmail(email) == null) {
                        System.out.print("Введённый Вами адрес некорректен. Повторите ввод: ");
                        email = scanner.nextLine();
                    }
                    service.getUserByEmail(email).setRole(Role.USER);
                    System.out.printf("\nПользователь %s разблокирован.", email);
                    waitRead();
                    showAdminMenu();
                    break;
                case 3:
                    System.out.println("\nДобавление нового автомобиля");
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    int year;
                    double price;

                    while (true) {
                        System.out.print("Введите год выпуска: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Вы ввели не год. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            year = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Введите стоимость аренды (в евро): ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Стоимость некорректна. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        }
                    }
                    service.addCar(model, year, price);
                    System.out.printf("\nАвтомобиль %s %d г.в. успешно добавлен.", model, year);
                    waitRead();
                    showAdminMenu();
                    break;
                case 4:
                    System.out.println("\nУкажите ID автомобиля для изменения стоимости аренды:");
                    System.out.println(service.getFreeCars().toString());
                    int id = getSelection();
                    while (service.getByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    while (true) {
                        System.out.print("Введите новую стоимость аренды (в евро): ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Стоимость некорректна. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        }
                    }
                    service.updateCarPrice(id, price);
                    System.out.printf("\nСтоимость аренды автомобиля %s %d г.в. установлена в сумме %.2f евро в день.",
                            service.getByID(id).getModel(), service.getByID(id).getYear(), price);
                    waitRead();
                    showAdminMenu();
                    break;
                default:
                    System.out.print("Введённое Вами число некорректно. ");
                    choice = getSelection();
                    break;
            }
        }
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