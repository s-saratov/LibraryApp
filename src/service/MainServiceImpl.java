package service;

import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

import java.time.LocalDate;

public class MainServiceImpl implements MainService{

    // Поля
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private User activeUser;

    // Конструктор


    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // Методы

    @Override
    public void addBook(String author, String title, int year, String publisher) {
        bookRepository.addBook(author, title, year, publisher);
    }

    @Override
    public User registerUser(String email, String password) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAllBooksSortedByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooksSortedByTitle(String title) {
        return null;
    }

    @Override
    public Book getBookByID(int id) {
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getFreeBooks() {
        return null;
    }

    @Override
    public int borrowingTerm(int id) {
        return 0;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getActiveUser() {
        return null;
    }

    @Override
    public MyList<User> getUsersByRole(Role... roles) {
        return null;
    }

    @Override
    public String getBorrowersEmail(int id) {
        return "";
    }

    @Override
    public boolean takeBook(int id) {
        return false;
    }

    @Override
    public boolean takeBook(int id, LocalDate NewDate) {
        return false;
    }

    @Override
    public boolean updateBorrowDate(int id, LocalDate NewBorrowDate) {
        return false;
    }

    @Override
    public boolean returnBook(int id) {
        return false;
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public Book deleteBookByID(int id) {
        return null;
    }
}