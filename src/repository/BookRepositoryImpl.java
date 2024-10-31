package repository;

import model.Book;
import model.BookStatus;
import utils.MyList;
import utils.MyArrayList;

public class BookRepositoryImpl implements BookRepository {

    private final MyList<Book> books = new MyArrayList<>();

    @Override
    public void addBook(String author, String title, int year, String publisher) {
         // Присвою уникальный ID на основе текущего размера списка
        int id = books.size() + 1;
        Book book = new Book(id, author, title, year, publisher);
        books.add(book);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getByID(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        //Если книга не найдена, возвращаю null
        return null;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().contains(author)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getFreeBooks() {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getStatus() == BookStatus.AVAILABLE) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        // Удаляю книгу из списка
        books.remove(book);
    }
}
