import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import view.Menu;

public class LibraryApp {

  public static void main(String[] args) {

    // Создаём экземпляры классов приложения
    UserRepository userRepository = new UserRepositoryImpl();
    BookRepository bookRepository = new BookRepositoryImpl();
    MainService service = new MainServiceImpl(bookRepository, userRepository);

    Menu menu = new Menu(service);

    menu.run();
  }

}