package repository;

import java.util.concurrent.atomic.AtomicInteger;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

/**
 * Репозиторий для управления пользователями. Предоставляет методы для добавления, поиска и фильтрации
 * пользователей в системе.
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class UserRepositoryImpl implements UserRepository {

  /**
   * Список пользователей.
   */
  private final MyList<User> users;


  /**
   * Объект, отвечающий за генерацию уникальных ID.
   */
  private final AtomicInteger currentID = new AtomicInteger(1);


  public UserRepositoryImpl() {
    this.users = new MyArrayList<>();


    // Добавить администраторов по умолчанию.
    this.addUser("admin@mail.com", "A*,5QReA-J1CDo[", Role.ADMIN);


    // Добавить пользователей по умолчанию.
    this.addUser("max@mail.com", "!2345Qwerty");
    this.addUser("user2@mail.com", "6789()Asdf");

    // Добавить заблокированного пользователя
    this.addUser("sm@sergey-mavrodi.com", "MmM-4EVER!", Role.BLOCKED);

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
    if (email == null || password == null) {
      return null;
    }

    User user = new User(this.currentID.getAndIncrement(), email, password);

    this.users.add(user);

    return user;
  }


  /**
   * Добавляет нового пользователя с указанной электронной почтой, паролем и ролью в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @param role     Роль пользователя.
   * @return Объект пользователя {@code User}.
   */
  public User addUser(String email, String password, Role role) {
    if (email == null || password == null || role == null) {
      return null;
    }

    User user = new User(this.currentID.getAndIncrement(), email, password, role);

    this.users.add(user);

    return user;
  }


  /**
   * Проверяет, существует ли пользователь с заданным адресом электронной почты.
   *
   * @param email Электронная почта для проверки.
   * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
   */
  @Override
  public boolean isEmailExists(String email) {
    if (email == null) {
      return false;
    }

    for (User user : this.users) {
      if (user.getEmail().equals(email)) {
        return true;
      }
    }

    return false;
  }


  /**
   * Возвращает список всех пользователей.
   *
   * @return {@code MyList<User>} Список всех пользователей.
   */
  public MyList<User> getAllUsers() {
    return this.users;
  }


  /**
   * Возвращает список пользователей, имеющих указанные роли.
   *
   * @param roles Роли, по которым осуществляется фильтрация пользователей.
   * @return {@code MyList<User>} Список пользователей, соответствующих указанным ролям.
   */
  @Override
  public MyList<User> getUsersByRole(Role... roles) {
    MyList<User> result = new MyArrayList<>();

    for (Role role : roles) {
      for (User user : this.users) {
        if (user.getRole().equals(role)) {
          result.add(user);
        }
      }
    }

    return result;
  }


  /**
   * Находит и возвращает пользователя по заданному адресу электронной почты.
   *
   * @param email Электронная почта пользователя для поиска.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  @Override
  public User getUserByEmail(String email) {
    if (email == null) {
      return null;
    }

    for (User user : this.users) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }

    return null;
  }


  /**
   * Находит и возвращает пользователя по его уникальному идентификатору.
   *
   * @param id Уникальный идентификатор пользователя.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  @Override
  public User getUserById(int id) {
    for (User user : this.users) {
      if (user.getId() == id) {
        return user;
      }
    }

    return null;
  }

}
