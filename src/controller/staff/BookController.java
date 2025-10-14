package controller.staff;

import config.ConfigDB;
import service.BookService;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class BookController {

    public BookController() {
        Connection conn = ConfigDB.openConnection();
        BookService bookService = new BookService(conn);

        String name = JOptionPane.showInputDialog("Enter the book name:");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }
        if (EmptyValidator.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "Book name cannot be empty.");
            return;
        }

        String stockStr = JOptionPane.showInputDialog("Enter book stock (number):");
        if (stockStr == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }
        if (EmptyValidator.isEmpty(stockStr)) {
            JOptionPane.showMessageDialog(null, "Book stock cannot be empty.");
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) {
                JOptionPane.showMessageDialog(null, "Stock must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid stock number.");
            return;
        }

        int statusOption = JOptionPane.showConfirmDialog(null,
                "Is the book available?", "Book status",
                JOptionPane.YES_NO_OPTION);

        boolean status = (statusOption == JOptionPane.YES_OPTION);

        boolean created = bookService.createBook(name, stock, status);

        if (created) {
            JOptionPane.showMessageDialog(null, "Book registered successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error creating book. Try again.");
        }
    }
}
