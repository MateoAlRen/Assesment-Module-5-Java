package dao;

import dao.Interfaces.LoanCRUD;
import domain.BookLoan;

import javax.swing.*;
import java.sql.*;

public class LoanDAO implements LoanCRUD {

    private static Connection connection;

    // Constructor
    public LoanDAO(Connection connection) {
        LoanDAO.connection = connection;
    }

    @Override
    public boolean createLoan(BookLoan loan) {
        String sql = "INSERT INTO book_loan (user_id, book_id, book_quantity, loan_date) VALUES (?, ?, ?, NOW())";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loan.getUser_id());
            stmt.setInt(2, loan.getBook_id());
            stmt.setInt(3, loan.getBook_quantity());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating loan: " + e.getMessage());
            return false;
        }
    }

    @Override
    public BookLoan getLoanById(int id) {
        String sql = "SELECT * FROM book_loan WHERE loan_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BookLoan(
                        rs.getInt("loan_id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getInt("book_quantity"),
                        rs.getTimestamp("loan_date")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting loan: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean readLoan() {
        String sql = "SELECT * FROM book_loan";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder message = new StringBuilder("Book Loans:\n");
            boolean hasLoans = false;

            while (rs.next()) {
                hasLoans = true;

                int loanId = rs.getInt("loan_id");
                int userId = rs.getInt("user_id");
                int bookId = rs.getInt("book_id");
                int bookQuantity = rs.getInt("book_quantity");
                Timestamp loanDate = rs.getTimestamp("loan_date");

                message.append("Loan ID: ").append(loanId)
                        .append(" | User ID: ").append(userId)
                        .append(" | Book ID: ").append(bookId)
                        .append(" | Quantity: ").append(bookQuantity)
                        .append(" | Date: ").append(loanDate)
                        .append("\n");
            }

            if (hasLoans) {
                JOptionPane.showMessageDialog(null, message.toString(), "Book Loans", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "There are no book loans registered!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading loans: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateLoan(BookLoan loan) {
        String sql = "UPDATE book_loan SET user_id=?, book_id=?, book_quantity=? WHERE loan_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loan.getUser_id());
            stmt.setInt(2, loan.getBook_id());
            stmt.setInt(3, loan.getBook_quantity());
            stmt.setInt(4, loan.getLoan_id());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating loan: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteLoan(int id) {
        String sql = "DELETE FROM book_loan WHERE loan_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting loan: " + e.getMessage());
            return false;
        }
    }

    public boolean readLoansByUserId(int userId) {
        String sql = "SELECT * FROM user_loans WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder message = new StringBuilder("Your Loans: " + "\n");
            boolean hasLoans = false;

            while (rs.next()) {
                hasLoans = true;

                int loanId = rs.getInt("loan_id");
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                int quantity = rs.getInt("book_quantity");

                message.append("Loan ID: ").append(loanId)
                        .append(" | Book ID: ").append(bookId)
                        .append(" | Name: ").append(bookName)
                        .append(" | Quantity: ").append(quantity)
                        .append("\n");
            }

            if (hasLoans) {
                JOptionPane.showMessageDialog(null, message.toString(), "User Loans", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "This user has no registered loans.");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading loans for user: " + e.getMessage());
            return false;
        }
    }



}
