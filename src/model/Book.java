package model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

  // Идентификационный номер
  private final int id;

  // Автор книги
  private String author;

  // Название книги
  private String title;

  // Год издания
  private int year;

  // Издательство
  private String publisher;

  // Статус книги
  private BookStatus status;

  // Пользователь, взявший книгу
  User borrower;

  // Дата взятия книги
  private LocalDate borrowDate;

  // Конструктор класса Book
  public Book(int id, String author, String title, int year, String publisher) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.year = year;
    this.publisher = publisher;
    this.status = BookStatus.AVAILABLE; // Книга доступна по умолчанию
    this.borrower = null;
  }

  // Геттеры и сеттеры (не буду расписывать какой для чего)

  public int getId() {
    return id; // Геттер для id (без сеттера по моему так по условию)
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

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

  public User getBorrower() {
    return borrower;
  }

  public void setBorrower(User borrower) {
    this.borrower = borrower;
  }

  // Удаляет связь с читателем
  public void removeBorrower() {
    this.borrower = null;
  }

  // Переопределение "equals" для сравнения объектов
  @Override
  public boolean equals(Object o) {
    if (this == o) return true; // Сравнение ссылок
    if (o == null || getClass() != o.getClass()) return false; // Проверка типа объекта
    Book book = (Book) o; // Приведение типа
    return id == book.id && year == book.year && // Сравнение полей
           Objects.equals(author, book.author) &&
           Objects.equals(title, book.title) &&
           Objects.equals(publisher, book.publisher);
  }


  public void setBusy(boolean b) {
    if (b) this.status = BookStatus.BORROWED;
    else this.status = BookStatus.AVAILABLE;
  }

  public boolean isBusy() {
    if (status == BookStatus.AVAILABLE) return false;
    else return true;
  }


  // Возвращает дату, когда была взята книга
  public LocalDate getBorrowDate() {
    return borrowDate;
  }

  // Устанавливает новую дату взятия книги
  public void setBorrowDate(LocalDate newBorrowDate) {
    this.borrowDate = newBorrowDate;
  }

  //  toString -> в строку

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            ", year=" + year +
            ", publisher='" + publisher + '\'' +
            ", status=" + status +
            ", borrower=" + borrower +
            '}';
  }
}