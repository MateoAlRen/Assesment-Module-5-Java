package service;

import dao.BookDAO;
import dao.LoanDAO;
import domain.BookLoan;
import domain.LibraryBooks;
import java.sql.Connection;

public class LoanService {
    private final BookDAO bookDAO;
    private final LoanDAO loanDAO;

    public LoanService(Connection conn) {
        this.bookDAO = new BookDAO(conn);
        this.loanDAO = new LoanDAO(conn);
    }

    public boolean createLoan(int userId, int bookId, int quantity) {
        if (userId <= 0 || bookId <= 0 || quantity <= 0) {
            System.out.println("Invalid data.");
            return false;
        }

        LibraryBooks book = bookDAO.getBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (!book.isBook_status()) {
            System.out.println("Book is not available.");
            return false;
        }

        if (book.getBook_stock() < quantity) {
            System.out.println("Not enough stock.");
            return false;
        }

        BookLoan loan = new BookLoan();
        loan.setUser_id(userId);
        loan.setBook_id(bookId);
        loan.setBook_quantity(quantity);

        boolean created = loanDAO.createLoan(loan);

        if (created) {
            int newStock = book.getBook_stock() - quantity;
            bookDAO.updateStock(bookId, newStock);
            System.out.println("Loan created successfully.");
            return true;
        } else {
            System.out.println("Error creating loan.");
            return false;
        }
    }
}

