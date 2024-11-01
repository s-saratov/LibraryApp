package test.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.Utils;

/**
 * Тестовый класс для проверки реализации UtilsTest.
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class UtilsTest {

  @ParameterizedTest
  @CsvFileSource(resources = "emails_valid.csv")
  public void testIsValidEmail(String email) {
    Assertions.assertTrue(Utils.isValidEmail(email));
  }


  @ParameterizedTest
  @CsvFileSource(resources = "emails_invalid.csv")
  public void testIsInvalidEmail(String email) {
    Assertions.assertFalse(Utils.isValidEmail(email));
  }


  @ParameterizedTest
  @CsvFileSource(resources = "passwords_valid.csv")
  public void testIsValidPassword(String password) {
    Assertions.assertTrue(Utils.isValidPassword(password));
  }


  @ParameterizedTest
  @CsvFileSource(resources = "passwords_invalid.csv")
  public void testIsNotValidPassword(String password) {
    Assertions.assertFalse(Utils.isValidPassword(password));
  }

}