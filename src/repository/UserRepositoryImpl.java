package repository;

import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

public class UserRepositoryImpl implements UserRepository {

  /**
   * Список пользователей.
   */
  private final MyList<User> users;


  public UserRepositoryImpl() {
    this.users = new MyArrayList<>();

    // Добавить администраторов по умолчанию.
    users.add(new User("admin@mietwagen.de", "A*,5QReA-J1CDo[", Role.ADMIN));


    // Добавить пользователей по умолчанию.
    users.add(new User("test@mietwagen.de", "!2345Qwerty"));
    users.add(new User("sm@sergey-mavrodi.com", "MmM-4EVER!"));
  }


  /**
   * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @return Объект пользователя {@code User}.
   */
  @Override
  public User addUser(String email, String password) {
    return null;
  }


  /**
   * Проверяет, существует ли пользователь с заданным адресом электронной почты.
   *
   * @param email Электронная почта для проверки.
   * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
   */
  @Override
  public boolean isEmailExists(String email) {
    return false;
  }


  /**
   * Возвращает список пользователей, имеющих указанные роли.
   *
   * @param roles Роли, по которым осуществляется фильтрация пользователей.
   * @return {@code MyList<User>} Список пользователей, соответствующих указанным ролям.
   */
  @Override
  public MyList<User> getUsersByRole(Role... roles) {
    return null;
  }


  /**
   * Находит и возвращает пользователя по заданному адресу электронной почты.
   *
   * @param email Электронная почта пользователя для поиска.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  @Override
  public User getUserByEmail(String email) {
    return null;
  }

}
