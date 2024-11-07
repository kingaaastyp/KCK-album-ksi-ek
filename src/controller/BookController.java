
package controller;

import model.Book;
import model.BookRepository;
import view.BookView;

import java.util.List;

public class BookController {
    private BookRepository bookRepository;
    private BookView bookView;

    public BookController(BookRepository bookRepository, BookView bookView) {
        this.bookRepository = bookRepository;
        this.bookView = bookView;
    }

    public void start() {
        bookView.displayWelcomeScreen();
        boolean running = true;
        while (running) {
            bookView.displayMenu();
            int choice =-1;
            try {
                choice = bookView.getMenuSelection();
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy wybór. Proszę podać liczbę.");
                continue;
            }

            switch (choice) {
                case 1:
                    List<Book> allBooks = bookRepository.getBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("Brak książek w bazie danych.");
                    } else {
                        bookView.displayBooks(allBooks);
                    }
                    break;

                case 2:
                    String title = bookView.promptForInput("Podaj tytuł: ");
                    List<Book> booksByTitle = bookRepository.searchByTitle(title);
                    if (booksByTitle.isEmpty()) {
                        System.out.println("Nie znaleziono książek o podanym tytule.");
                    } else {
                        bookView.displayBooks(booksByTitle);
                    }
                    break;

                case 3:
                    String author = bookView.promptForInput("Podaj autora: ");
                    List<Book> booksByAuthor = bookRepository.searchByAuthor(author);
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("Nie znaleziono książek o podanym autorze.");
                    } else {
                        bookView.displayBooks(booksByAuthor);
                    }
                    break;

                case 4:
                    Book newBook = bookView.promptForBookDetails();
                    bookRepository.addBook(newBook);
                    System.out.println("Książka została dodana.");
                    break;

                case 5:
                    List<Book> booksToRemove = bookRepository.getBooks();
                    if (booksToRemove.isEmpty()) {
                        System.out.println("Brak książek do usunięcia.");
                    } else {
                        Book bookToRemove = bookView.promptForBookRemoval(booksToRemove);
                        if (bookToRemove != null) {
                            bookRepository.removeBook(bookToRemove);
                            System.out.println("Książka została usunięta.");
                        } else {
                            System.out.println("Anulowano usunięcie książki.");
                        }
                    }
                    break;

                case 6:
                    int sortOption;
                    try {
                        sortOption = Integer.parseInt(bookView.promptForInput("Sortuj po: 1 - tytule, 2 - autorze: "));
                    } catch (NumberFormatException e) {
                        System.out.println("Nieprawidłowy wybór. Proszę podać liczbę.");
                        break;
                    }
                    if (sortOption == 1) {
                        bookRepository.sortBooksByTitle();
                        System.out.println("Książki posortowane po tytule.");
                    } else if (sortOption == 2) {
                        bookRepository.sortBooksByAuthor();
                        System.out.println("Książki posortowane po autorze.");
                    } else {
                        System.out.println("Nieprawidłowa opcja sortowania.");
                    }
                    break;

                case 7:
                    String filterAuthor = bookView.promptForInput("Podaj autora do filtrowania: ");
                    List<Book> filteredBooks = bookRepository.filterByAuthor(filterAuthor);
                    if (filteredBooks.isEmpty()) {
                        System.out.println("Brak książek pasujących do filtra autora.");
                    } else {
                        bookView.displayBooks(filteredBooks);
                    }
                    break;

                case 8:
                    List<Book> books = bookRepository.getBooks();
                    if (books.isEmpty()) {
                        System.out.println("Brak książek do wyświetlenia.");
                    } else {
                        bookView.displayBooks(books); // wyświetl książki z numerami
                        int bookNumber;
                        try {
                            bookNumber = Integer.parseInt(bookView.promptForInput("Wybierz numer książki, aby zobaczyć szczegóły: "));
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy numer książki.");
                            break;
                        }
                        if (bookNumber > 0 && bookNumber <= books.size()) {
                            bookView.displayBookDetails(books.get(bookNumber - 1));
                        } else {
                            System.out.println("Nieprawidłowy numer książki.");
                        }
                    }
                    break;

                case 9:
                    running = false;
                    System.out.println("Wyjście z aplikacji.");
                    break;

                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        }
    }
}
        /*import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Book;
import java.io.IOException;
import java.util.List;

public class BookView {
    private final Screen screen;
    private final MultiWindowTextGUI gui;

    public BookView() throws IOException {
        this.screen = new DefaultTerminalFactory().createScreen();
        this.screen.startScreen();
        this.gui = new MultiWindowTextGUI(screen);
    }

    public void displayWelcomeScreen() {
        MessageDialog.showMessageDialog(gui, "Witamy", "Witamy w aplikacji zarządzania książkami!", MessageDialogButton.OK);
    }

    public void displayMenu() {
        // Użycie MessageDialog do wyświetlenia menu
        String menu = "1. Wyświetl wszystkie książki\n" +
                "2. Wyszukaj książkę po tytule\n" +
                "3. Wyszukaj książkę po autorze\n" +
                "4. Dodaj książkę\n" +
                "5. Usuń książkę\n" +
                "6. Sortuj książki\n" +
                "7. Filtruj książki po autorze\n" +
                "8. Wyświetl szczegóły książki\n" +
                "9. Wyjdź";
        MessageDialog.showMessageDialog(gui, "Menu", menu, MessageDialogButton.OK);
    }

    public int getMenuSelection() throws NumberFormatException {
        String input = promptForInput("Wybierz opcję: ");
        return Integer.parseInt(input);
    }

    public void displayBooks(List<Book> books) {
        StringBuilder bookList = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            bookList.append(i + 1).append(". Tytuł: ").append(book.getTitle())
                    .append(", Autor: ").append(book.getAuthor()).append("\n");
        }
        MessageDialog.showMessageDialog(gui, "Lista książek", bookList.toString(), MessageDialogButton.OK);
    }

    public String promptForInput(String prompt) {
        TextInputDialog inputDialog = new TextInputDialog(gui, prompt, "", "");
        inputDialog.showDialog(gui);
        return inputDialog.getText();
    }

    public Book promptForBookDetails() {
        String title = promptForInput("Podaj tytuł: ");
        String author = promptForInput("Podaj autora: ");
        return new Book(title, author);
    }

    public Book promptForBookRemoval(List<Book> books) {
        displayBooks(books);
        int index = Integer.parseInt(promptForInput("Wybierz numer książki do usunięcia: ")) - 1;
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    public void displayBookDetails(Book book) {
        String details = "Tytuł: " + book.getTitle() + "\nAutor: " + book.getAuthor();
        MessageDialog.showMessageDialog(gui, "Szczegóły książki", details, MessageDialogButton.OK);
    }

    public void exitApplication() {
        try {
            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
