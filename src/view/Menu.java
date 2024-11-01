package view;

import model.BookStatus;
import model.Role;
import model.User;
import service.MainService;
import utils.Utils;

import java.time.LocalDate;
import java.util.Scanner;


public class Menu {

    // Поля

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    // Конструктор

    public Menu(MainService service) {
        this.service = service;
    }

    // Методы

    /**
     * Запускает программу.
     */
    public void run() {
        this.showMainMenu();
    }

    // Главное меню!
    private void showMainMenu() {
        System.out.println("\u001B[32m\nДобро пожаловать в главное меню библиотеки \"Знания века\"!\u001B[0m");
        System.out.println("1. Меню книг");
        System.out.println("2. Меню пользователя");
        System.out.println("3. Меню администратора");
        System.out.println("0. Выход из системы");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 1:
                    showBookMenu();
                    break;
                case 2:
                    showUserMenu();
                    break;
                case 3:
                    if (service.getActiveUser() == null) {
                        System.out.print("\u001B[31m\nДоступ запрещён! Вы не авторизованы как администратором системы." +
                                "\u001B[0m\n");
                        waitRead();
                        showMainMenu();
                        break;
                    }
                    showAdminMenu();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите ввод.");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void showBookMenu() {
        System.out.println(
                "\nМеню книг:\n" +
                        "1. Показать список всех книг\n" +
                        "2. Показать список всех книг, доступных для взятия к прочтению\n" +
                        "3. Поиск книги по названию\n" +
                        "4. Поиск книги по автору\n" +
                        "5. Взять книгу\n" +
                        "6. Вернуть книгу\n" +
                        "0. Вернуться в предыдущее меню"
        );
        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 1:
                    System.out.println("\nСписок всех книг библиотеки:\n");
                    System.out.println(Utils.printBooks(service.getAllBooks()));
                    waitRead();
                    showBookMenu();
                    break;
                case 2:
                    System.out.println("\nСписок всех книг библиотеки, доступных для взятия к прочтению:\n");
                    System.out.println(Utils.printBooks(service.getFreeBooks()));
                    waitRead();
                    showBookMenu();
                    break;
                case 3:
                    System.out.print("Введите название (или часть): ");
                    String searchValue = scanner.nextLine();
                    System.out.println("\nРезультаты поиска:\n");
                    System.out.println(Utils.printBooks(service.getBooksByTitle(searchValue)));
                    waitRead();
                    showBookMenu();
                    break;
                case 4:
                    System.out.print("Введите автора (или часть): ");
                    searchValue = scanner.nextLine();
                    System.out.println("\nРезультаты поиска:\n");
                    System.out.println(Utils.printBooks(service.getBooksByAuthor(searchValue)));
                    waitRead();
                    showBookMenu();
                    break;
                case 5:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showBookMenu();
                        break;
                    }
                    System.out.println("\nСписок всех книг библиотеки, доступных для взятия к прочтению:\n");
                    System.out.println(Utils.printBooks(service.getFreeBooks()));
                    System.out.println("Укажите ID книги, которую хотите Взять.");
                    int id = getSelection();
                    while (!service.takeBook(id)) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    System.out.printf("\nКнига %s успешно взята Вами для прочтения\n",
                            service.getBookByID(id).getTitle());
                    waitRead();
                    showBookMenu();
                    break;
                case 6:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showBookMenu();
                        break;
                    }

                    if (service.getActiveUser().getUserBooks().isEmpty()) {
                        System.out.println("\nВ настоящее время у Вас не имеется ни одной книги из нашей библиотеки.");
                        waitRead();
                        showBookMenu();
                        break;
                    }
                    System.out.println(Utils.printBooks(service.getActiveUser().getUserBooks()));
                    System.out.println("Укажите ID книги, которую Вы хотите вернуть:");
                    id = getSelection();
                    while (!service.returnBook(id)) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    System.out.printf("\nКнига %s успешно возвращена Вами в библиотеку.\n",
                            service.getBookByID(id).getTitle());
                    waitRead();
                    showBookMenu();
                    break;
                case 0:
                    showMainMenu();
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите ввод.");
                    choice = getSelection();
                    break;
            }
        }

    }

    /**
     * Отображает меню "Меню пользователя" в консоли.
     */
    private void showUserMenu() {
        System.out.println(
                "\nМеню пользователя:\n" +
                        "1. Вход в систему\n" +
                        "2. Регистрация нового пользователя\n" +
                        "3. Выход из системы\n" +
                        "4. Показать список взятых книг\n" + // (по названию)
//                        "5. Показать список взятых книг (по сроку возврата)\n" +
                        "0. Вернуться в предыдущее меню\n"
        );

        int choice = this.getSelection();

        while (true) {
            switch (choice) {
                // Вернуться в предыдущее меню
                case 0:
                    this.showMainMenu();
                    break;

                // Вход в систему
                case 1:
                    this.service.logout();
                    this.showLoginMenu();
                    break;

                // Регистрация нового пользователя
                case 2:
                    this.service.logout();
                    this.showAccountCreationMenu();
                    break;

                // Выход из системы
                case 3:
                    this.service.logout();
                    this.showMainMenu();
                    break;

                // Показать список взятых книг ДОДЕЛАТЬ (отсортированных по названию) ДОДЕЛАТЬ
                case 4:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showUserMenu();
                        break;
                    }
                    System.out.println("\nCписок взятых Вами книг:\n");
                    System.out.println(Utils.printBooks(service.getActiveUser().getUserBooks()));
                    this.waitRead();
                    this.showUserMenu();
                    break;

                // Показать список взятых книг (по сроку возврата)
                // TODO: доделать список по сроку возврата!
//                case 5:
//
//                    System.out.println("\nПоказать список взятых книг (по сроку возврата):");
//                    System.out.println(this.service.getActiveUser().getBorrowedBooksOrderedByReturnDate());
//                    this.waitRead();
//                    this.showUserMenu();
//                    break;

                default:
                    System.out.print("Введённое Вами число некорректно!");
                    choice = this.getSelection();
                    break;
            }
        }
    }

    /**
     * Отображает меню "Login" в консоли.
     */
    private void showLoginMenu() {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nВход в систему.");
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();
        if (Utils.isValidEmail(string)) email = string;
        else {
            while (!Utils.isValidEmail(string)) {
                System.out.print("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();
        if (Utils.isValidPassword(string)) password = string;
        else {
            while (!Utils.isValidPassword(string)) {
                System.out.print("Введённый Вами пароль не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        if (service.loginUser(email, password)) {
            showMainMenu();
        } else showUserMenu();

    }


    /**
     * Отображает меню "AccountCreation" в консоли.
     */
    private void showAccountCreationMenu() {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nРегистрация нового пользователя.");
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();
        if (Utils.isValidEmail(string)) email = string;
        else {
            while (!Utils.isValidEmail(string)) {
                System.out.printf("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();
        if (Utils.isValidPassword(string)) password = string;
        else {
            while (!Utils.isValidPassword(string)) {
                System.out.println("Введённый Вами пароль не соответствует требованиям. Повторите ввод:");
                string = scanner.nextLine();
            }
        }

        User user = service.registerUser(email, password);
        if (user == null) {
            System.out.println("Регистрация не пройдена. Повторите попытку.");
            showAccountCreationMenu();
        } else {
            System.out.println("Регистрация успешно завершена. Теперь Вы можете войти в систему.");
            showUserMenu();
        }

    }

    /**
     * Отображает меню "Меню администратора" в консоли.
     */
    private void showAdminMenu() {
        if (this.service.getActiveUser().getRole() != Role.ADMIN) {

            System.out.print("\u001B[31m\nДоступ запрещён! Вы не являетесь администратором системы.\u001B[0m\n");
            this.waitRead();
            this.showMainMenu();
        }

        System.out.println(
                "Меню администратора\n" +
                        "1. Посмотреть список всех пользователей\n" +
                        "2. Заблокировать пользователя\n" +
                        "3. Разблокировать пользователя\n" +
                        "4. Добавить новую книгу\n" +
                        "0. Вернуться в предыдущее меню"
        );

        int choice = getSelection();

        while (true) {
            switch (choice) {

                // Вернуться в предыдущее меню
                case 0:
                    this.showMainMenu();
                    break;

                case 1:
                    System.out.println("\nПолный список пользователей системы:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(Role.ADMIN, Role.USER, Role.BLOCKED)));
                    waitRead();
                    showAdminMenu();
                    break;

                // Заблокировать пользователя
                case 2:
                    System.out.println("\nВыберете пользователя для блокировки по ID:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(Role.ADMIN, Role.USER)));

                    int id = getSelection();

                    while (this.service.getUserByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }

                    this.service.getUserByID(id).setRole(Role.BLOCKED);
                    System.out.printf("\nПользователь %s заблокирован.\n", this.service.getUserByID(id).getEmail());
                    this.waitRead();
                    this.showAdminMenu();
                    break;

                // Разблокировать пользователя
                case 3:
                    System.out.println("\nВыберете пользователя для разблокирования по ID:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(Role.BLOCKED)));

                    id = getSelection();

                    while (this.service.getUserByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }

                    this.service.getUserByID(id).setRole(Role.USER);
                    System.out.printf("\nПользователь %s разблокирован.\n", this.service.getUserByID(id).getEmail());
                    this.waitRead();
                    this.showAdminMenu();
                    break;

                // Добавить новую книгу
                case 4:
                    System.out.println("\nДобавление новой книги.");

                    System.out.print("Введите название книги: ");
                    String title = this.scanner.nextLine();

                    int year;

                    while (true) {
                        System.out.print("Введите год издания: ");

                        if (this.scanner.hasNextInt()) {
                            year = this.scanner.nextInt();
                            this.scanner.nextLine();  // Очистка буфера после успешного ввода числа

                            if (year <= 0 || year > 2024) {
                                System.out.println("\nВы ввели некорректный год.\nПожалуйста, повторите ввод.\n");
                            } else {
                                break;  // Выход из цикла при корректном годе
                            }
                        } else {
                            System.out.println("\nНекорректный ввод. Пожалуйста, введите число.");
                            this.scanner.nextLine();  // Очистка буфера после некорректного ввода
                        }
                    }

                    System.out.print("Введите автора книги: ");
                    String author = this.scanner.nextLine();

                    System.out.print("Введите издателя книги: ");
                    String publisher = this.scanner.nextLine();

                    this.service.addBook(author, title, year, publisher);
                    System.out.printf(
                            "\nКнига: %s - %s %d г. (%s) успешно добавлена!\n",
                            author,
                            title,
                            year,
                            publisher
                    );

                    this.waitRead();
                    this.showAdminMenu();
                    break;
                // Изменить дату возврата книги
//                case 4:
//                    System.out.println("\nИзменение даты возврата книги.\n");
//
//                    System.out.println(this.service.getFreeBooks().toString());
//                    System.out.println("\nВедите id книги книги:");
//
//                    int id = -1;
//                    while (true) {
//                        id = this.getSelection();
//
//                        if (this.service.getBookByID(id) != null) {
//                            break;
//                            System.out.print("\nВы ввели некорректный ID.\nПожалуйста, повторите ввод.\n");
//                        }
//                    }
//
//                        // TODO: get date
//                        LocalDate date = null;
//
//                        this.service.updateBorrowDate(id, date);
//
//                        System.out.print("\nВы успешно обновили дату возврата!\n");
//                        this.waitRead();
//                        this.showAdminMenu();
//                        break;

                default:
                    System.out.print("Упс! Неправильное значение, попробуйте еще раз.");
                    choice = this.getSelection();
                    break;
            }

        }
    }


    /**
     *
     */
    private void waitRead() {
        System.out.print("Для продолжения нажмите Enter...");
        scanner.nextLine();
    }

    /**
     * Возвращает только введённое число (предотвращая ошибку в случае ввода символов).
     *
     * @return Выбор.
     */
    private int getSelection() {
        int selection;
        while (true) {
            System.out.print("Введите Ваш выбор: ");
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