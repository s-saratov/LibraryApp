package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {

  //Добавление книги в общий список
  boolean addBook(String author, String title, int year, String publisher);

  //Получение информации о книгах
  MyList<Book> getAllBooks();
  Book getByID(int id);
  MyList<Book> getBooksByTitle(String title);
  MyList<Book> getBooksByAuthor(String author);
  MyList<Book> getFreeBooks();
  MyList<Book> getBorrowedBooks();
  String toString();

  //Удаление книги
  void deleteBook(Book book);
}