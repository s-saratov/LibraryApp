package view;

import model.Role;
import service.MainService;
import java.time.LocalDate;
import java.util.Scanner;


public class Menu {

  private final MainService service;
  private final Scanner scanner = new Scanner(System.in);


  public Menu(MainService service) {
    this.service = service;
  }


  /**
   *
   */
  private void waitRead() {
    System.out.print("\nДля продолжения нажмите Enter...");
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
      System.out.print("Введите Ваш выбор:");

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


  /**
   * Отображает меню "Welcome" в консоли.
   */
  private void showWelcomeMenu() {
  }


  /**
   * Отображает меню "Login" в консоли.
   */
  private void showLoginMenu() {
  }


  /**
   * Отображает меню "AccountCreation" в консоли.
   */
  private void showAccountCreationMenu() {
  }


  /**
   * Отображает меню "Main" в консоли.
   */
  private void showMainMenu() {
  }


  /**
   * Отображает меню "Меню пользователя" в консоли.
   */
  private void showUserMenu() {
    System.out.println(
        "Меню пользователя:\n" +
        "1. Вход в систему\n" +
        "2. Регистрация нового пользователя\n" +
        "3. Выход из системы\n" +
        "4. Показать список взятых книг (по названию)\n" +
        "5. Показать список взятых книг (по сроку возврата)\n" +
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
          this.showWelcomeMenu();
          break;

        // Показать список взятых книг (отсортированных по названию)
        case 4:
          System.out.println("\nПоказать список взятых книг (по названию):");
          System.out.println(this.service.getActiveUser().getBorrowedBooksOrderedByTitle());
          this.waitRead();
          this.showUserMenu();
          break;

        // Показать список взятых книг (по сроку возврата)
        case 5:
          System.out.println("\nПоказать список взятых книг (по сроку возврата):");
          System.out.println(this.service.getActiveUser().getBorrowedBooksOrderedByReturnDate());
          this.waitRead();
          this.showUserMenu();
          break;

        default:
          System.out.print("Введённое Вами число некорректно!");
          choice = this.getSelection();
          break;
      }
    }
  }


  /**
   * Отображает меню "Меню администратора" в консоли.
   */
  private void showAdminMenu() {
    if (this.service.getActiveUser().getRole() != Role.ADMIN) {
      System.out.print("Доступ запрещён!\nВы не являетесь администратором системы.");
      this.waitRead();
      this.showMainMenu();
    }

    System.out.println(
        "Меню администратора\n" +
        "1. Заблокировать пользователя\n" +
        "2. Разблокировать пользователя\n" +
        "3. Добавить новую книгу\n" +
        "4. Изменить дату возврата книги\n" +
        "0. Вернуться в предыдущее меню"
    );

    int choice = getSelection();

    while (true) {
      switch (choice) {

        // Вернуться в предыдущее меню
        case 0:
          this.showMainMenu();
          break;


        // Заблокировать пользователя
        case 1:
          System.out.println("\nВыберете пользователя для блокировки:");
          System.out.println(this.service.getUsersByRole(Role.ADMIN, Role.USER).toString());

          System.out.print("Введите электронную почту блокируемого пользователя: ");
          String email = this.scanner.nextLine();

          while (this.service.getUserByEmail(email) == null) {
            System.out.print("Введённый Вами адрес некорректен. Повторите ввод: ");
            email = this.scanner.nextLine();
          }

          this.service.getUserByEmail(email).setRole(Role.BLOCKED);
          System.out.printf("\nПользователь %s заблокирован.", email);
          this.waitRead();
          this.showAdminMenu();
          break;


        // Разблокировать пользователя
        case 2:
          System.out.println("\nВыберете пользователя для разблокирования:");
          System.out.println(this.service.getUsersByRole(Role.BLOCKED, Role.USER).toString());

          System.out.print("Введите электронную почту разблокируемого пользователя: ");
          email = this.scanner.nextLine();

          while (this.service.getUserByEmail(email) == null) {
            System.out.print("Введённый Вами адрес некорректен. Повторите ввод: ");
            email = this.scanner.nextLine();
          }

          this.service.getUserByEmail(email).setRole(Role.USER);
          System.out.printf("\nПользователь %s разблокирован.", email);
          this.waitRead();
          this.showAdminMenu();
          break;


        // Добавить новую книгу
        case 3:
          System.out.println("\nДобавление новой книги.");

          System.out.print("Введите название книги:");
          String title = this.scanner.nextLine();

          int year;

          while (true) {
            System.out.print("Введите год выпуска:");
            year = (this.scanner.hasNextInt() ? this.scanner.nextInt() : -1);

            if (year <= 0 || year > 2024) {
              System.out.println("\nВы ввели некорректный год.\nПожалуйста, повторите ввод.\n");
              this.scanner.nextLine();
            } else break;
          }

          System.out.print("Введите автора книги:");
          String author = this.scanner.nextLine();

          System.out.print("Введите издателя книги:");
          String publisher = this.scanner.nextLine();

          this.service.addBook(author, title, year, publisher);
          System.out.printf(
              "\nКнига: %s - %s %d г. (%s) успешно добавлена!",
              author,
              title,
              year,
              title
          );

          this.waitRead();
          this.showAdminMenu();
          break;


        // Изменить дату возврата книги
        case 4:
          System.out.println("\nИзменение даты возврата книги.\n");

          System.out.println(this.service.getFreeBooks().toString());
          System.out.println("\nВедите id книги книги:");

          int id = -1;
          while (true) {
            id = this.getSelection();

            if (this.service.getBookByID(id) != null) {
              break;
            }

            System.out.print("\nВы ввели некорректный ID.\nПожалуйста, повторите ввод.\n");
          }

          // TODO: get date
          LocalDate date = null;

          this.service.updateBorrowDate(id, date);

          System.out.print("\nВы успешно обновили дату возврата!\n");
          this.waitRead();
          this.showAdminMenu();
          break;

        default:
          System.out.print("Упс! Неправильное значение, попробуйте еще раз.");
          choice = this.getSelection();
          break;
      }
    }

  }


  /**
   * Запускает программу.
   */
  public void run() {
    this.showWelcomeMenu();
  }

}
