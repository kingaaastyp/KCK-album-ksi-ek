package view;

import model.Book;

import java.util.List;
import java.util.Scanner;
public class BookView {
    private Scanner scanner = new Scanner(System.in);

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public void displayWelcomeScreen() {
        System.out.println(ANSI_CYAN + "=================================" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "|    WITAJ W APLIKACJI KSIĄŻKI   |" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "=================================" + ANSI_RESET);

        // Rysunek książki w stylu ASCII
        System.out.println("  |____________________________________________________|");
        System.out.println("  | __     __   ____   ___ ||  ____    ____     _  __  |");
        System.out.println(" ||  |__ |--|_| || |_|   |||_|**|*|__|+|+||___| ||  | |");
        System.out.println(" ||==|^^||--| |=||=| |=*=||| |~~|~|  |=|=|| | |~||==| |");
        System.out.println(" ||  |##||  | | || | |JRO|||-|  | |==|+|+||-|-|~||__| |");
        System.out.println(" ||__|__||__|_|_||_|_|___|||_|__|_|__|_|_||_|_|_||__|_|");
        System.out.println(" ||_______________________||__________________________|");
        System.out.println(" | _____________________  ||      __   __  _  __    _ |");
        System.out.println(" ||=|=|=|=|=|=|=|=|=|=|=| __..\\/ |  |_|  ||#||==|  / /|");
        System.out.println(" || | | | | | | | | | | |/\\ \\  \\\\|++|=|  || ||==| / / |");
        System.out.println(" ||_|_|_|_|_|_|_|_|_|_|_/_/\\_.___\\__|_|__||_||__|/_/__|");
        System.out.println(" |____________________ /\\~()/()~//\\ __________________|");
        System.out.println(" | __   __    _  _     \\_  (_ .  _/ _    ___     _____|");
        System.out.println(" ||~~|_|..|__| || |_ _   \\ //\\\\ /  |=|__|~|~|___| | | |");
        System.out.println(" ||--|+|^^|==|1||2| | |__ /\\ __ /\\__| |==|x|x|+|+|=|=|=|");
        System.out.println(" ||__|_|__|__|_||_|_| /  \\ \\  / /  \\_|__|_|_|_|_|_|_|_|");
        System.out.println(" |_________________ _/    \\/\\/\\/    \\_ _______________|");
        System.out.println(" | _____   _   __  |/      \\../      \\|  __   __   ___|");
        System.out.println(" ||_____|_| |_|##|_||   |   \\/ __|   ||_|==|_|++|_|-|||");
        System.out.println(" ||______||=|#|--| |\\   \\   o    /   /| |  |~|  | | |||");
        System.out.println(" ||______||_|_|__|_|_\\   \\  o   /   /_|_|__|_|__|_|_|||");
        System.out.println(" |_________ __________\\___\\____/___/___________ ______|");
        System.out.println(" |__    _  /    ________     ______           /| _ _ _|");
        System.out.println(" |\\ \\  |=|/   //    /| //   /  /  / |        / ||%|%|%|");
        System.out.println(" | \\ /\\ |*/  .//____//.//   /__/__/ (_)      /  ||=|=|=|");
        System.out.println("__|  \\/\\|/   /(____|/ //                    /  /||~|~|~|__");
        System.out.println(" |___\\_/   /________//   ________         /  / ||_|_|_|");
        System.out.println(" |___ /   (|________/   |\\_______\\       /  /| | |______|");
        System.out.println("     /                  \\|________)     /  / | |");

        // Podpis "Album Książek"
        System.out.println("\n" + ANSI_YELLOW + "           ███████╗██╗     ███████╗██╗  ██╗██╗  ██╗██╗  ██╗██╗  ██╗██╗  ██╗██╗  ██╗");
        System.out.println("           ██╔════╝██║     ██╔════╝██║  ██║██║  ██║██║  ██║██║  ██║██║  ██║██║  ██║");
        System.out.println("           █████╗  ██║     █████╗  ███████║███████║███████║███████║███████║███████║");
        System.out.println("           ██╔══╝  ██║     ██╔══╝  ██╔══██║██╔══██║██╔══██║██╔══██║██╔══██║██╔══██║");
        System.out.println("           ███████╗███████╗███████╗██║  ██║██║  ██║██║  ██║██║  ██║██║  ██║██║  ██║");
        System.out.println("           ╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝" + ANSI_RESET);
    }



    public void displayMenu() {
        System.out.println("\u001B[36m=== Aplikacja Książki ===\u001B[0m");
        System.out.println("1. Wyświetl wszystkie książki");
        System.out.println("2. Wyszukaj książki po tytule");
        System.out.println("3. Wyszukaj książki po autorze");
        System.out.println("4. Dodaj nową książkę");
        System.out.println("5. Usuń książkę");
        System.out.println("6. Sortuj książki");
        System.out.println("7. Filtruj książki");
        System.out.println("8. Wyświetl szczegóły wybranej książki");
        System.out.println("9. Wyjdź");
        System.out.println("\u001B[36m========================\u001B[0m");

    }


    public int getMenuSelection() {
        int selection = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Wybierz opcję: ");
                selection = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowe dane. Proszę podać liczbę.");
            }
        }
        return selection;
    }

    public String promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public Book promptForBookDetails() {
        String title = promptForInput("Podaj tytuł: ");
        String author = promptForInput("Podaj autora: ");
        String genre = promptForInput("Podaj gatunek: ");
        int pageCount = promptForPositiveInteger("Podaj liczbę stron: ");
        int year = promptForYear("Podaj rok wydania: ");
        String description = promptForInput("Podaj opis: ");

        return new Book(title, author, genre, pageCount, year, description);
    }


    public Book promptForBookRemoval(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Brak książek do usunięcia.");
            return null;
        } else {
            displayBooks(books);
            String title = promptForInput("Podaj tytuł książki do usunięcia: ");
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            System.out.println("Książka nie została znaleziona.");
            return null;
        }
    }
    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("\u001B[31mBrak książek do wyświetlenia.\u001B[0m");
        } else {
            System.out.println("\u001B[36m===== Lista Książek =====\u001B[0m");
            int index = 1;
            for (Book book : books) {
                System.out.printf("\u001B[34m%d. %s\u001B[0m - Autor: %s\n", index++, book.getTitle(), book.getAuthor());
            }
            System.out.println("\u001B[36m=========================\u001B[0m");
        }
    }

    public void displayBookDetails(Book book) {
        System.out.println("\u001B[33m==============================\u001B[0m");
        System.out.printf("\u001B[32mTytuł:\u001B[0m %s\n", book.getTitle());
        System.out.printf("\u001B[32mAutor:\u001B[0m %s\n", book.getAuthor());
        System.out.printf("\u001B[32mOpis:\u001B[0m %s\n", book.getDescription());
        System.out.println("\u001B[33m==============================\u001B[0m");
    }
    private int promptForPositiveInteger(String prompt) {
        int number = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine());
                if (number <= 0) {
                    throw new NumberFormatException(); // wymusza ponowną próbę
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Proszę podać prawidłową liczbę dodatnią.");
            }
        }
        return number;
    }

    // Metoda do pobierania roku
    private int promptForYear(String prompt) {
        int year = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                year = Integer.parseInt(scanner.nextLine());
                if (year < 0 || year > java.time.Year.now().getValue()) {
                    throw new NumberFormatException(); // wymusza ponowną próbę
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Proszę podać prawidłowy rok wydania (0 lub większy, nie większy niż obecny rok).");
            }
        }
        return year;
    }
}

