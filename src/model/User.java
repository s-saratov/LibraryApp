package model;

import utils.MyArrayList;
import utils.MyList;
import utils.Utils;

public class User {

  // Идентификационный номер
  private final int id;

  // Адрес электронной почты
  private String email;

  // Пароль
  private String password;

  // Роль пользователя
  private Role role;

  // Список книг пользователя
  private MyList<Book> userBooks;

  // Конструктор User с параметром роли
  public User(int id, String email, String password, Role role) {
    this.id = id;
    this.email = Utils.isValidEmail(email) ? email : null;
    this.password = Utils.isValidPassword(password) ? password : null;
    this.role = role != null ? role : Role.USER;
    /* Если роль не была задана при создании пользователя - по умолчанию "USER" */
    this.userBooks = new MyArrayList<>();   // Инициализация списка книг у пользователя
  }

  public User(int id, String email, String password) {
    this(id, email, password, Role.USER);
  }

  // Геттеры и сеттеры

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String setEmail(String email) {
    if (ValidationUtils.isValidEmail(email)) {
      this.email = email;
      return email;
    } else {
      return null;
    }
  }

  public String getPassword() {
    return password;
  }

  public String setPassword(String password) {
    if (ValidationUtils.isValidPassword(password)) {
      this.password = password;
      return password;
    } else {
      return null;
    }
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  // Добавление книги в список пользователя
  public void addBookToUserBooks(Book book) {
    userBooks.add(book);
  }

  // Удаление книги из списка пользователя
  public void removeBookFromUserBooks(Book book) {
    userBooks.remove(book);
  }

  /**
   * Выдает список всех книг пользователя.
   *
   * @return список книг
   */
  public MyList<Book> getUserBooks() {
    return userBooks;
  }

  /**
   * Возвращает список книг пользователя, упорядоченные по названию в алфавитном порядке.
   *
   * @return Список книг, отсортированный по названию.
   */
  public MyList<Book> getBorrowedBooksOrderedByTitle() {
    MyArrayList<Book> result = new MyArrayList<>();

    if (this.userBooks == null || this.userBooks.isEmpty()) {
      return result;
    }

    // Копируем книги в новый список
    for (int i = 0; i < this.userBooks.size(); i++) {
      result.add(this.userBooks.get(i));
    }

    // Пузырьковая сортировка
    int n = result.size();

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1 - i; j++) {
        // Сравниваем названия книг
        if (result.get(j).getTitle().compareToIgnoreCase(result.get(j + 1).getTitle()) > 0) {
          Book temp = result.get(j);
          result.set(j, result.get(j + 1));
          result.set(j + 1, temp);
        }
      }
    }

    return result;
  }

  /**
   * Выдает список книг пользователя, упорядоченные по срокам возврата.
   *
   * @return Список книг, отсортированный по срокам возврата.
   */
  public MyList<Book> getBorrowedBooksOrderedByReturnDate() {
    // TODO: getBorrowedBooksOrderedByReturnDate().
    return null;
  }

  // Переопределение метода toString для печати информации о пользователе
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", роль=" + role +
            ", список книг=" + userBooks +
            '}';
  }
}