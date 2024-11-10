import controller.BookController;
import model.BookRepository;
import view.BookView;

public class Main {
    public static void main(String[] args) {
        // Tworzymy instancję BookRepository
        BookRepository bookRepository = new BookRepository("books.txt");

        // Tworzymy instancję BookView bez kontrolera
        BookView bookView = new BookView(null);

        // Tworzymy instancję BookController, przekazując BookRepository i BookView
        BookController bookController = new BookController(bookRepository, bookView);

        // Ustawiamy kontroler w BookView, aby była widoczna relacja z BookController
        bookView.setController(bookController);
    }
}
