package dao;

import dao.Interfaces.BookCRUD;
import domain.LibraryBooks;

import javax.swing.*;
import java.sql.*;

public class BookDAO implements BookCRUD {

    private static Connection connection;

    // Constructor
    public BookDAO(Connection connection) {
        BookDAO.connection = connection;
    }

    @Override
    public boolean createBook(LibraryBooks book) {
        String sql = "INSERT INTO library_books (book_name, book_stock, book_status) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getBook_name());
            stmt.setInt(2, book.getBook_stock());
            stmt.setBoolean(3, book.isBook_status());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    @Override
    public LibraryBooks getBookById(int id) {
        String sql = "SELECT * FROM library_books WHERE book_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LibraryBooks(
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getInt("book_stock"),
                        rs.getBoolean("book_status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting book: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean readBook() {
        String sql = "SELECT * FROM library_books";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder message = new StringBuilder("Library Books:\n");
            boolean hasBooks = false;

            while (rs.next()) {
                hasBooks = true;

                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                int bookStock = rs.getInt("book_stock");
                boolean bookStatus = rs.getBoolean("book_status");

                message.append("ID: ").append(bookId)
                        .append(" - Name: ").append(bookName)
                        .append(" - Stock: ").append(bookStock)
                        .append(" - Status: ").append(bookStatus ? "Available" : "Not Available")
                        .append("\n");
            }

            if (hasBooks) {
                JOptionPane.showMessageDialog(null, message.toString(), "Library Books", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "There are no books registered!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading books: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBook(LibraryBooks book) {
        String sql = "UPDATE library_books SET book_name=?, book_stock=?, book_status=? WHERE book_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getBook_name());
            stmt.setInt(2, book.getBook_stock());
            stmt.setBoolean(3, book.isBook_status());
            stmt.setInt(4, book.getBook_id());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM library_books WHERE book_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStock(int bookId, int newStock) {
        String sql = "UPDATE library_books SET book_stock = ? WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, bookId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating stock: " + e.getMessage());
            return false;
        }
    }

    public boolean increaseStock(int bookId, int quantity) {
        String sql = "UPDATE library_books SET book_stock = book_stock + ? WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, bookId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating book stock: " + e.getMessage());
            return false;
        }
    }

}
