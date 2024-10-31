package service;

import model.Book;
import model.Role;
import model.User;
import model.BookStatus;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

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

  // Методы

  // === CREATE ===

  // Добавляет книгу в общий список
  @Override
  public void addBook(String author, String title, int year, String publisher) {
    bookRepository.addBook(author, title, year, publisher);
  }

  // Регистрирует пользователя на основании переданных адреса электронной почты и пароля, возвращает экземпляр класса
  @Override
  public User registerUser(String email, String password) {
    if (email == null || password == null) return null; // исключение передачи null
    if (userRepository.isEmailExists(email)) {
      System.out.println("Email already exists");
      return null;
    }
    User user = userRepository.addUser(email, password);
    return user;
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
    if (id < 0) return null; // исключаем передачу некорректных данных
    return bookRepository.getByID(id);
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

  // Возвращает количество дней, которое книга находится у читателя
  @Override
  public int borrowingTerm(int id) {
    // TODO: написать реализацию!
    return 0;
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


  /**
   * @param roles
   * @param user
   * @return
   */
  @Override
  public MyList<User> getUsersByRole(Role roles, Role user) {
    return null;
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
    if (id < 0) return false; // исключаем передачу некорректных данных
    Book book = bookRepository.getByID(id);
    if (book == null || book.isBusy()) return false;
    book.setStatus(BookStatus.BORROWED);
    activeUser.addBookToUserBooks(book);
    return true;
  }

  // Осуществляет взятие книги в прочтение и возвращает статус успеха операции (с конкретной датой, отличающейся от сегодняшней)
  @Override
  public boolean takeBook(int id, LocalDate NewDate) {
    // TODO: написать реализацию!!!
    return false;
  }

  // Изменяет дату взятия книги в прочтение и возвращает статус успеха операции
  @Override
  public boolean updateBorrowDate(int id, LocalDate NewBorrowDate) {
    Book book = bookRepository.getByID(id);
    if (NewBorrowDate == null || id < 0) {
      return false;
    }

    book.setBorrowDate(NewBorrowDate);

    return true;
  }


  // Осуществляет возврат книги от читателя и возвращает статус успеха операции
  @Override
  public boolean returnBook(int id) {
    if (id < 0) return false; // исключаем передачу некорректных данных
    if (activeUser.getUserBooks().isEmpty()) return false;
    if (!activeUser.getUserBooks().contains(bookRepository.getByID(id))) return false;

    activeUser.removeBookFromUserBooks(bookRepository.getByID(id));
    bookRepository.getByID(id).setBusy(false);
    return true;
  }

  // Осуществляет вход пользователя в систему и возвращает статус успеха операции
  @Override
  public boolean loginUser(String email, String password) {
    if (email == null || password == null) return false; // исключение передачи null

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