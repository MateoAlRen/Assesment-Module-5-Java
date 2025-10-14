package service;

import dao.BookDAO;
import domain.LibraryBooks;
import errors.BadRequestException;
import errors.NotFoundException;

import javax.swing.*;
import java.sql.Connection;

public class UpdateBookService {

    private final Connection conn;
    private final BookDAO bookDAO;

    public UpdateBookService(Connection conn) {
        this.conn = conn;
        this.bookDAO = new BookDAO(conn);
    }

    public boolean updateBook(LibraryBooks book) {
        if (book.getBook_id() <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid book ID.");
            throw new BadRequestException("Invalid book ID");
        }

        if (book.getBook_name() == null || book.getBook_name().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book name cannot be empty.");
            throw new BadRequestException("Book name is empty");
        }

        if (book.getBook_stock() < 0) {
            JOptionPane.showMessageDialog(null, "Book stock cannot be negative.");
            throw new BadRequestException("Stock is negative");
        }

        LibraryBooks existing = bookDAO.getBookById(book.getBook_id());
        if (existing == null) {
            JOptionPane.showMessageDialog(null, "Book not found.");
           throw new NotFoundException("Book doesn´t exists");
        }

        boolean success = bookDAO.updateBook(book);

        if (!success) {
            JOptionPane.showMessageDialog(null, "Database error: Could not update book.");
        }

        return success;
    }
}
