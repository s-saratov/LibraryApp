package model;

import java.util.Objects;

public class Book {
    private final int id;               // Идентификационный номер
    private String author;              // Автор книги
    private String title;               // Название книги
    private int year;                   // Год издания
    private String publisher;           // Издательство
    private BookStatus status;          // Статус книги

    // Конструкторр класса Book

    public Book(int id, String author, String title, int year, String publisher) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.status = BookStatus.AVAILABLE;
    }

    // Геттеры и сеттерыы

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    // Переопределение метода equals для сравнения объектов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(publisher, book.publisher);
    }

    // Переопределение toString для строки

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", publisher='"