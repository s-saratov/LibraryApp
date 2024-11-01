package service;

import model.Book;
import model.Role;
import model.User;
import utils.MyList;

import java.time.LocalDate;

public interface MainService {

    // === CREATE ===

    // Добавляет книгу в список
    boolean addBook(String author, String title, int year, String publisher);

    // Регистрирует пользователя на основании переданных адреса электронной почты и пароля, возвращает экземпляр класса
    User registerUser (String email, String password);

    // === READ ===

    // Возвращает текущий список всех книг
    MyList<Book> getAllBooks();

    // Возвращает текущий список всех книг, отсортированный по автору
    MyList<Book> getAllBooksSortedByAuthor(String author);

    // Возвращает текущий список всех книг, отсортированный по названию
    MyList<Book> getAllBooksSortedByTitle(String title);

    // Возвращает книгу по ID
    Book getBookByID(int id);

    // Возвращает список книг по названию
    public MyList<Book> getBooksByTitle(String title);

    // Возвращает список книг по автору
    MyList<Book> getBooksByAuthor(String author);

    // Возвращает список невзятых книг
    MyList<Book> getFreeBooks();

    // Возвращает количество дней, которое книга находится у читателя
    int borrowingTerm (int id);

    // Возвращает объект пользователя по адресу электронной почты
    public User getUserByEmail(String email);

    // Возвращает объект пользователя по ID
    public User getUserByID(int id);

    // Возвращает активного пользователя
    public User getActiveUser();

    // Возвращает список пользователей по заданным ролям
    public MyList<User> getUsersByRole(Role roles, Role user);

    // Возвращает список пользователей по заданным ролям
    MyList<User> getUsersByRole(Role... roles);

    // Принимает ID книги и возвращает адрес электронной почты пользователя, у которого она находится
    User getBorrowersEmail (int id);

    // === UPDATE ===

    // Осуществляет взятие книги в прочтение и возвращает статус успеха операции (сегодняшней датой)
    boolean takeBook(int id);

    // Осуществляет взятие книги в прочтение и возвращает статус успеха операции (с конкретной датой, отличающейся от сегодняшней)
    boolean takeBook(int id, LocalDate NewDate);

    // Изменяет дату взятия книги в прочтение и возвращает статус успеха операции
    boolean updateBorrowDate(int id, LocalDate NewBorrowDate);

    // Осуществляет возврат книги от читателя и возвращает статус успеха операции
    boolean returnBook (int id);

    // Осуществляет вход пользователя в систему и возвращает статус успеха операции
    boolean loginUser(String email, String password);

    // Осуществляет выход пользователя из системы
    void logout();

    // === DELETE ===

    // Удаляет книгу по id
    Book deleteBookByID(int id);

}