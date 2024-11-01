import model.Book;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.Utils;
import view.Menu;

public class LibraryApp {

  public static void main(String[] args) {

    // Создаём экземпляры классов приложения
    UserRepository userRepository = new UserRepositoryImpl();
    BookRepository bookRepository = new BookRepositoryImpl();
    MainService service = new MainServiceImpl(bookRepository, userRepository);
    Menu menu = new Menu(service);

    service.getBookByID(1).setBusy(true);
    service.getUserByID(2).addBookToUserBooks(bookRepository.getByID(1));
    service.getBookByID(7).setBusy(true);
    service.getUserByID(2).addBookToUserBooks(bookRepository.getByID(7));

//    for (Book book : bookRepository.getAllBooks()) {
//      System.out.println(book.getStatus());
//    }

    menu.run();

//    String str1 = "Apple";
//    String str2 = "Banana";
//
//    int result = str1.compareTo(str2);
//    System.out.println(result);
//
//    if (result < 0) {
//      System.out.println(str1 + " предшествует " + str2);
//    } else if (result > 0) {
//      System.out.println(str1 + " следует за " + str2);
//    } else {
//      System.out.println(str1 + " и " + str2 + " равны");
//    }


  }

}