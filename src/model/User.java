package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final int id;                     // Идентификационный номер
    private String email;                      // Адрес электронной почты
    private String password;                   // Пароль
    private Role role;                         // Роль пользователя
    private List<Book> userBooks;             // Список книг пользователя

    // Конструктор класса User
    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = Role.USER;                 // По умолчанию роль "Пользователь"
        this.userBooks = new ArrayList<>();   // Инициализация списка книг
    }

    // Геттеры и сеттеры

    public int getId() {
        return id; // Геттер для id, без сеттера
    }

    public String getEmail() {
        return email; // Геттер для email
    }

    public void setEmail(String email) {
        this.email = email; // Сеттер для email
    }

    public String getPassword() {
        return password; // Геттер для пароля
    }

    public void setPassword(String password) {
        this.password = password; // Сеттер для пароля
    }

    public Role getRole() {
        return role; // Геттер для роли
    }

    public void setRole(Role role) {
        this.role = role; // Сеттер для роли
    }

    public List<Book> getUserBooks() {
        return userBooks; // Геттер для списка книг
    }

    // Метод для добавления книги в список пользователя
    public void addBookToUserBooks(Book book) {
        userBooks.add(book); // Добавление книги
    }

    // Метод для удаления книги из списка пользователя
    public void removeBookFromUserBooks(Book book) {
        userBooks.remove(book); // Удаление книги
    }

    // Переопределение метода toString для получения строкового представления объекта
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", userBooks=" + userBooks +
                '}';
    }
}