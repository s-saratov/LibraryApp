package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    // Идентификационный номер
    private final int id;

    // Адрес электронной почты
    private String email;

    // Пароль
    private String password;

    // Роль пользователя
    private Role role;

    // Список книг пользователя
    private List<Book> userBooks;

    // Конструктор User
    public User(int id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : Role.USER;
        /*Если роль не была задана при создании пользователя - по умолчанию "USER" */
        this.userBooks = new ArrayList<>();   // Инициализация списка книг у пользователя
    }



    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Book> getUserBooks() {
        return userBooks;
    }

    // Добавление книг в список пользователя
    public void addBookToUserBooks(Book book) {

        userBooks.add(book);

    }

    // Удаление книги из списка пользователя
    public void removeBookFromUserBooks(Book book) {

        userBooks.remove(book);

    }

    // toString -> cnhjrf
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", роль=" + role +
                ", список книг=" + userBooks +
                '}';
    }
}