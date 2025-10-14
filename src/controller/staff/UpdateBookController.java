package controller.staff;

import config.ConfigDB;
import domain.LibraryBooks;
import service.UpdateBookService;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class UpdateBookController {

    public UpdateBookController() {
        Connection conn = ConfigDB.openConnection();
        UpdateBookService bookService = new UpdateBookService(conn);

        String idStr = JOptionPane.showInputDialog("Enter the book ID to update:");
        if (idStr == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }
        if (EmptyValidator.isEmpty(idStr)) {
            JOptionPane.showMessageDialog(null, "Book ID cannot be empty.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "Book ID must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID number.");
            return;
        }

        String name = JOptionPane.showInputDialog("Enter the new book name:");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }
        if (EmptyValidator.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "Book name cannot be empty.");
            return;
        }

        String stockStr = JOptionPane.showInputDialog("Enter the new book stock:");
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
                JOptionPane.showMessageDialog(null, "Stock cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid stock number.");
            return;
        }

        int statusOption = JOptionPane.showConfirmDialog(
                null,
                "Is the book available?",
                "Book Status",
                JOptionPane.YES_NO_OPTION
        );

        boolean status = (statusOption == JOptionPane.YES_OPTION);


        LibraryBooks book = new LibraryBooks();
        book.setBook_id(id);
        book.setBook_name(name);
        book.setBook_stock(stock);
        book.setBook_status(status);


        boolean updated = bookService.updateBook(book);

        if (updated) {
            JOptionPane.showMessageDialog(null, "Book updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error updating book. Try again.");
        }
    }
}
