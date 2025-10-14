package controller;

import config.ConfigDB;
import service.BookService;

import javax.swing.*;
import java.sql.Connection;

public class ShowBooksController {
    public ShowBooksController() {
        Connection conn = ConfigDB.openConnection();
        BookService bookService = new BookService(conn);

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Do you want to see all registered books?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = bookService.ShowBooks();

            if (!success) {
                JOptionPane.showMessageDialog(null, "No books to show or an error occurred.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
        }
    }
}
