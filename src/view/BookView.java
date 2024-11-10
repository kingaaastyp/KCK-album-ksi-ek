package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import controller.BookController;
import model.Book;
import java.util.List;

public class BookView extends JFrame implements KeyListener {
    private JTextArea displayArea;
    private int selectedIndex = 0;
    private String[] menuOptions = {
            "1. Wyświetl wszystkie książki",
            "2. Wyszukaj książki po tytule",
            "3. Wyszukaj książki po autorze",
            "4. Dodaj książkę",
            "5. Usuń książkę",
            "6. Sortuj książki",
            "7. Filtruj książki",
            "8. Wyświetl szczegóły książki",
            "9. Edytuj książkę",
            "10. Wyjdź"
    };
    private boolean awaitingInput = false;
    private String inputPrompt = "";
    private BookController controller;

    public BookView(BookController controller) {
        this.controller = controller;
        setTitle("Album Książek");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        displayArea.setEditable(false);
        displayArea.addKeyListener(this);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        showMenu();
    }

    public void setController(BookController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        displayArea.setText("=== MENU ===\n");
        for (int i = 0; i < menuOptions.length; i++) {
            displayArea.append((i == selectedIndex ? "-> " : "") + menuOptions[i] + (i == selectedIndex ? " <-\n" : "\n"));
        }
        awaitingInput = false;
    }

    public void displayBooks(List<Book> books) {
        displayArea.setText("=== Lista Książek ===\n");
        for (Book book : books) {
            displayArea.append("Tytuł: " + book.getTitle() + "\n" +
                    "Autor: " + book.getAuthor() + "\n" +
                    "Gatunek: " + book.getGenre() + "\n" +
                    "Ilość stron: " + book.getPageCount() + "\n" +
                    "Rok wydania: " + book.getYear() + "\n" +
                    "Opis: " + book.getDescription() + "\n\n");
        }
        displayArea.append("Naciśnij ESC, aby wrócić do menu.");
    }

    public void promptForInput(String prompt) {
        awaitingInput = true;
        inputPrompt = prompt;
        displayArea.setEditable(true);
        displayArea.setText(prompt + "\n");
    }

    private void handleInput(String input) {
        awaitingInput = false;
        displayArea.setEditable(false);
        controller.handleUserInput(input, inputPrompt);
    }

    public void displayMessage(String message) {
        displayArea.setText(message + "\nNaciśnij ESC, aby wrócić do menu.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (awaitingInput && e.getKeyCode() == KeyEvent.VK_ENTER) {
            handleInput(displayArea.getText().replace(inputPrompt + "\n", "").trim());
        } else if (!awaitingInput) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
                    showMenu();
                    break;
                case KeyEvent.VK_DOWN:
                    selectedIndex = (selectedIndex + 1) % menuOptions.length;
                    showMenu();
                    break;
                case KeyEvent.VK_ENTER:
                    controller.selectOption(selectedIndex);
                    break;
                case KeyEvent.VK_ESCAPE:
                    showMenu();
                    break;
            }
        }
    }


    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
