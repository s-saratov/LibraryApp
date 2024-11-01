package test.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import repository.BookRepositoryImpl;
import repository.UserRepositoryImpl;
import service.MainServiceImpl;



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
   *
   * @param author
   * @param title
   * @param year
   * @param publisher
   */
  @ParameterizedTest
  @CsvFileSource(resources = "books_valid.csv", numLinesToSkip = 1)
  public void addValidBook(String author, String title, int year, String publisher) {
    boolean result = this.bookRepository.addBook(author, title, year, publisher);

    assertTrue(result, "Книга была добавлена.");
  }


  /**
   * Добавляет невалидные книги.
   *
   * @param author
   * @param title
   * @param year
   * @param publisher
   */
  @ParameterizedTest
  @CsvFileSource(resources = "books_invalid.csv", numLinesToSkip = 1)
  public void addInvalidBook(String author, String title, int year, String publisher) {
    boolean result = this.bookRepository.addBook(author, title, year, publisher);

    assertFalse(result, "Книга не была добавлена.");
  }

}
