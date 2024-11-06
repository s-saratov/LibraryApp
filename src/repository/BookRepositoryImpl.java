package repository;

import model.Book;
import model.BookStatus;
import utils.MyList;
import utils.MyArrayList;

import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // Поля

    private final MyList<Book> books;                                         // список книг
    private final AtomicInteger currentID = new AtomicInteger(1);   // объект, отвечающий за генерацию уникальных ID

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addBook("George Orwell", "1984", 1950, "Secker & Warburg");
        addBook("Harper Lee", "To Kill a Mockingbird", 1960, "J.B. Lippincott & Co.");
        addBook("J.R.R. Tolkien", "The Lord of the Rings", 1954, "George Allen & Unwin");
        addBook("Jane Austen", "Pride and Prejudice", 1813, "T. Egerton, Whitehall");
        addBook("Mark Twain", "The Adventures of Huckleberry Finn", 1885, "Chatto & Windus");
        addBook("F. Scott Fitzgerald", "The Great Gatsby", 1925, "Charles Scribner's Sons");
        addBook("Mary Shelley", "Frankenstein", 1823, "G. and W.B. Whittaker");
        addBook("Charlotte Brontë", "Jane Eyre", 1847, "Smith, Elder & Co.");
        addBook("Herman Melville", "Moby-Dick", 1851, "Harper & Brothers");
        addBook("Emily Brontë", "Wuthering Heights", 1847, "Thomas Cautley Newby");
    }

    @Override
    public boolean addBook(String author, String title, int year, String publisher) {
        Book book = new Book(currentID.getAndIncrement(), author, title, year, publisher);

        books.add(book);

        return true;
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
    public MyList<Book> getBorrowedBooks() {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getStatus() == BookStatus.BORROWED) {
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

    @Override
    public String toString() {
        String result = String.format("%-20s %-30s %-10s %-25s\n", "Author:", "Title", "Year", "Publisher");
        for (Book book : books) {
            result = result.concat(String.format("%-20s %-30s %-10d %-25s\n",
                    book.getAuthor(), book.getTitle(), book.getYear(), book.getPublisher()));
        }
        return result;
    }
}