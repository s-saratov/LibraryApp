package test.service;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepositoryImpl;
import repository.UserRepositoryImpl;
import service.MainServiceImpl;
import utils.MyList;
import utils.Utils;


/**
 * Тестовый класс для проверки реализации MainServiceImplTest.
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class MainServiceImplTest {
  private MainServiceImpl mainService;
  private BookRepositoryImpl bookRepository;
  private UserRepositoryImpl userRepository;


  /**
   * Выполняется перед каждым тестом. Инициализирует экземпляр {@code MainServiceImplTest}.
   */
  @BeforeEach
  public void init() {
    this.bookRepository = new BookRepositoryImpl();
    this.userRepository = new UserRepositoryImpl();
    this.mainService = new MainServiceImpl(this.bookRepository, this.userRepository);
  }


  /**
   * Добавляет валидные книги.
   */
  //  @Disabled
  @ParameterizedTest
  @CsvFileSource(resources = "books_valid.csv", numLinesToSkip = 1)
  public void testAddValidBook(String author, String title, int year, String publisher) {
    boolean result = this.mainService.addBook(author, title, year, publisher);

    assertTrue(result, "Книга была добавлена.");
  }


  /**
   *
   */
  @ParameterizedTest
  @CsvFileSource(resources = "users_valid.csv", numLinesToSkip = 1)
  public void testRegisterUser_True(String email, String password) {
    User result = this.mainService.registerUser(email, password);

    assertNotNull(result, "Ожидается экземпляр User для действительных учетных данных.");
    assertInstanceOf(User.class, result, "Ожидается экземпляр User.");
  }


  /**
   *
   */
  @ParameterizedTest
  @CsvFileSource(resources = "users_invalid.csv", numLinesToSkip = 1)
  public void testRegisterUser_False(String email, String password) {
    User result = this.mainService.registerUser(email, password);

    assertNull(result, "Ожидается null для недопустимых учетных данных.");
  }


  /**
   *
   */
  @Test
  public void testGetAllBooks() {
    MyList<Book> books = this.mainService.getAllBooks();

    assertTrue(books.size() > 0);
  }

}
