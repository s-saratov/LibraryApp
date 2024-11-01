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

  // === CREATE ===

  @Override
  public boolean addBook(String author, String title, int year, String publisher) {
    return bookRepository.addBook(author, title, year, publisher);
  }

  @Override
  public User registerUser(String email, String password) {
    if (email == null || password == null) return null;
    if (userRepository.isEmailExists(email)) {
      System.out.println("Email already exists");
      return null;
    }
    return userRepository.addUser(email, password);
  }

  // === READ ===

  @Override
  public MyList<Book> getAllBooks() {
    return bookRepository.getAllBooks();
  }

  @Override
  public MyList<Book> getAllBooksSortedByAuthor(String author) {
    // TODO: написать реализацию!
    return null;
  }

  @Override
  public MyList<Book> getAllBooksSortedByTitle(String title) {
    // TODO: написать реализацию!
    return null;
  }

  @Override
  public Book getBookByID(int id) {
    if (id < 0) return null;
    return bookRepository.getByID(id);
  }

  @Override
  public MyList<Book> getBooksByTitle(String title) {
    return bookRepository.getBooksByTitle(title);
  }

  @Override
  public MyList<Book> getBooksByAuthor(String author) {
    return bookRepository.getBooksByAuthor(author);
  }

  @Override
  public MyList<Book> getFreeBooks() {
    return bookRepository.getFreeBooks();
  }

  @Override
  public int borrowingTerm(int id) {
    // TODO: написать реализацию!
    return 0;
  }

  @Override
  public User getUserByID(int id) {
    return userRepository.getUserById(id);
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);
  }

  @Override
  public User getActiveUser() {
    return activeUser;
  }

  @Override
  public MyList<User> getUsersByRole(Role... roles) {
    return userRepository.getUsersByRole(roles);
  }

  @Override
  public User getBorrowersEmail(int id) {
    return userRepository.getUserById(id);
  }

  // === UPDATE ===

  @Override
  public boolean takeBook(int id) {
    if (id < 0) return false;
    Book book = bookRepository.getByID(id);
    if (book == null || book.isBusy()) return false;
    book.setStatus(BookStatus.BORROWED);
    activeUser.addBookToUserBooks(book);
    return true;
  }

  @Override
  public boolean takeBook(int id, LocalDate newDate) {
    if (id < 0 || newDate == null) return false;
    Book book = bookRepository.getByID(id);
    if (book == null || book.isBusy()) return false;

    book.setStatus(BookStatus.BORROWED);
    book.setBorrowDate(newDate);
    activeUser.addBookToUserBooks(book);
    return true;
  }

  @Override
  public boolean updateBorrowDate(int id, LocalDate newBorrowDate) {
    if (id < 0 || newBorrowDate == null) return false;
    Book book = bookRepository.getByID(id);
    book.setBorrowDate(newBorrowDate);
    return true;
  }

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
    activeUser.removeBookFromUserBooks(book);
    System.out.println("Книга успешно возвращена.");
    return true;
  }

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

  @Override
  public void logout() {
    activeUser = null;
  }

  // === DELETE ===

  @Override
  public Book deleteBookByID(int id) {
    Book book = bookRepository.getByID(id);
    if (book == null) return null;

    bookRepository.deleteBook(book);
    return book;
  }
}