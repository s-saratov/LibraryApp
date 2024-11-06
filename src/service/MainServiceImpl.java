package service;

import jdk.jshell.execution.Util;
import model.Book;
import model.Role;
import model.User;
import model.BookStatus;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;
import utils.Utils;

import java.time.LocalDate;

public class MainServiceImpl implements MainService {

  // Поля
  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private User activeUser;

  // Конструктор
  public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }

  // === CREATE ===

  // Добавляет книгу в список
  @Override
  public boolean addBook(String author, String title, int year, String publisher) {
    return bookRepository.addBook(author, title, year, publisher);
  }

  
  /**
   * Регистрирует пользователя на основании переданных адреса электронной почты и пароля, возвращает экземпляр класса.
   *
   * @param email
   * @param password
   * @return Экземпляр класса {@code User}.
   */
  @Override
  public User registerUser(String email, String password) {
    if (!Utils.isValidEmail(email) || !Utils.isValidPassword(password)) {
      System.out.println("Неверный email или password!");
      return null;
    }

    if (repUser.isEmailExists(email)) {
      System.out.println("Пользователь уже существует!");
      return null;
    }
    
    return this.userRepository.addUser(email, password);
  }

  
  // === READ ===

  // Возвращает текущий список всех книг
  @Override
  public MyList<Book> getAllBooks() {
    return bookRepository.getAllBooks();
  }

  // Возвращает текущий список всех книг, отсортированный по автору
  @Override
  public MyList<Book> getAllBooksSortedByAuthor(String author) {
    // TODO: написать реализацию!
    return null;
  }
  // Возвращает текущий список всех книг, отсортированный по названию
  @Override
  public MyList<Book> getAllBooksSortedByTitle(String title) {
    // TODO: написать реализацию!
    return null;
  }

  // Возвращает книгу по ID
  @Override
  public Book getBookByID(int id) {
    if (id < 0) return null;
    return bookRepository.getByID(id);
  }

  // Возвращает список книг по названию
  @Override
  public MyList<Book> getBooksByTitle(String title) {
    return bookRepository.getBooksByTitle(title);
  }

  // Возвращает список книг по автору
  @Override
  public MyList<Book> getBooksByAuthor(String author) {
    return bookRepository.getBooksByAuthor(author);
  }

  // Возвращает список невзятых книг
  @Override
  public MyList<Book> getFreeBooks() {
    return bookRepository.getFreeBooks();
  }

  // Возвращает список взятых книг
  @Override
  public MyList<Book> getBorrowedBooks() {
    return bookRepository.getBorrowedBooks();
  }

  // Возвращает количество дней, которое книга находится у читателя
  @Override
  public int borrowingTerm(int id) {
    // TODO: написать реализацию!
    return 0;
  }

  // Возвращает объект пользователя по ID
  @Override
  public User getUserByID(int id) {
    return userRepository.getUserById(id);
  }

  // Возвращает объект пользователя по адресу электронной почты
  @Override
  public User getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);
  }

  // Возвращает активного пользователя
  @Override
  public User getActiveUser() {
    return activeUser;
  }

  // Возвращает список пользователей по заданным ролям
  @Override
  public MyList<User> getUsersByRole(Role... roles) {
    return userRepository.getUsersByRole(roles);
  }

  // Принимает ID книги и возвращает адрес электронной почты пользователя, у которого она находится
  @Override
  public User getBorrowersEmail(int id) {
    return userRepository.getUserById(id);
  }

  // === UPDATE ===

  // Осуществляет взятие книги в прочтение и возвращает статус успеха операции (сегодняшней датой)
  @Override
  public boolean takeBook(int id) {
    if (id < 0) return false;
    Book book = bookRepository.getByID(id);
    if (book == null || book.isBusy()) return false;
    book.setStatus(BookStatus.BORROWED);
    book.setBorrower(activeUser);
    activeUser.addBookToUserBooks(book);
    return true;
  }

  // Осуществляет взятие книги в прочтение и возвращает статус успеха операции (с конкретной датой, отличающейся от сегодняшней)
  @Override
  public boolean takeBook(int id, LocalDate newDate) {
    if (id < 0 || newDate == null) return false;
    Book book = bookRepository.getByID(id);
    if (book == null || book.isBusy()) return false;

    book.setStatus(BookStatus.BORROWED);
    book.setBorrower(activeUser);
    book.setBorrowDate(newDate);
    activeUser.addBookToUserBooks(book);
    return true;
  }

  // Изменяет дату взятия книги в прочтение и возвращает статус успеха операции
  @Override
  public boolean updateBorrowDate(int id, LocalDate newBorrowDate) {
    if (id < 0 || newBorrowDate == null) return false;
    Book book = bookRepository.getByID(id);
    book.setBorrowDate(newBorrowDate);
    return true;
  }

  // Осуществляет возврат книги от читателя и возвращает статус успеха операции
  @Override
  public boolean returnBook(int id) {
    if (id < 0) return false;

    Book book = bookRepository.getByID(id);
    if (book == null) {
      System.out.println("Книга с таким ID не найдена.");
      return false;
    }

    if (activeUser == null || !activeUser.getUserBooks().contains(book)) {
      System.out.println("Книга не принадлежит активному пользователю.");
      return false;
    }

    if (book.getStatus() != BookStatus.BORROWED) {
      System.out.println("Книга не была взята.");
      return false;
    }

    book.setStatus(BookStatus.AVAILABLE);
    book.setBorrower(null);
    activeUser.removeBookFromUserBooks(book);
    System.out.println("Книга успешно возвращена.");
    return true;
  }

  // Осуществляет вход пользователя в систему и возвращает статус успеха операции
  @Override
  public boolean loginUser(String email, String password) {
    if (email == null || password == null) return false;

    User user = userRepository.getUserByEmail(email);
    if (user == null) {
      System.out.println("Адрес электронной почты введён неверно. Повторите ввод.");
      return false;
    }

    if (!user.getPassword().equals(password)) {
      System.out.println("Пароль введён неверно. Повторите ввод.");
      return false;
    }

    if (user.getRole() == Role.BLOCKED) {
      System.out.println("\nВход в систему невозможен. Ваша учётная запись заблокирована.");
      return false;
    }

    activeUser = user;
    return true;
  }

  // Осуществляет выход пользователя из системы
  @Override
  public void logout() {
    activeUser = null;
  }

  // === DELETE ===

  // Удаляет книгу по id
  @Override
  public Book deleteBookByID(int id) {
    Book book = bookRepository.getByID(id);
    if (book == null) return null;

    bookRepository.deleteBook(book);
    return book;
  }
}