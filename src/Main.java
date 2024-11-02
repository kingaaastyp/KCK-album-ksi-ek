import controller.BookController;
import model.BookRepository;
import view.BookView;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository("books.txt");
        BookView bookView = new BookView();
        BookController bookController = new BookController(bookRepository, bookView);

        bookController.start();
    }
}