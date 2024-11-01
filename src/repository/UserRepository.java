package repository;

import model.Role;
import model.User;
import utils.MyList;

/**
 * Интерфейс репозитория для управления пользователями. Предоставляет методы для добавления, поиска и фильтрации
 * пользователей в системе.
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public interface UserRepository {

  /**
   * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @return Объект пользователя {@code User}.
   */
  User addUser(String email, String password);


  /**
   * Проверяет, существует ли пользователь с заданным адресом электронной почты.
   *
   * @param email Электронная почта для проверки.
   * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
   */
  boolean isEmailExists(String email);


  /**
   * Возвращает список пользователей, имеющих указанные роли.
   *
   * @param roles Роли, по которым осуществляется фильтрация пользователей.
   * @return {@code MyList<User>} Список пользователей, соответствующих указанным ролям.
   */
  public MyList<User> getUsersByRole(Role... roles);


  /**
   * Находит и возвращает пользователя по заданному адресу электронной почты.
   *
   * @param email Электронная почта пользователя для поиска.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  public User getUserByEmail(String email);


  /**
   * Находит и возвращает пользователя по его уникальному идентификатору.
   *
   * @param id Уникальный идентификатор пользователя.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  public User getUserById (int id);

}
