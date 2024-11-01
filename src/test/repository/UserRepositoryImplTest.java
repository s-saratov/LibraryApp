//package test.repository;
//
//import model.User;
//import repository.UserRepositoryImpl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
///**
// * Тестовый класс для проверки реализации UserRepositoryImpl.
// *
// * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
// */
//public class UserRepositoryImplTest {
//  private UserRepositoryImpl userRepository;
//
//
//  /**
//   * Выполняется перед каждым тестом. Инициализирует экземпляр {@code UserRepositoryImpl}.
//   */
//  @BeforeEach
//  void setUp() {
//    userRepository = new UserRepositoryImpl();
//  }
//
//
//  /**
//   * Проверяет, что при добавлении нового пользователя метод addUser возвращает непустой объект {@code User} с
//   * корректными данными.
//   */
//  @Test
//  void addUser_ok() {
//    String email = "test@example.com";
//    String password = "password-123";
//    User user = userRepository.addUser(email, password);
//
//    assertNotNull(user, "User должен быть не null");
//    assertEquals(email, user.getEmail());
//    assertEquals(password, user.getPassword());
//  }
//
//
//  /**
//   * Проверяет, что при добавлении пользователя с уже существующим email метод addUser возвращает null.
//   */
//  @Test
//  void addUser_err() {
//    String email = "test@example.com";
//    String password = "password-123";
//    User result = userRepository.addUser(email, password);
//
//    assertNull(result, "User должен быть null, если email уже существует");
//  }
//
//}
