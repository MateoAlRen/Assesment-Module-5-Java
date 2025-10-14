package service;

import dao.LoanDAO;
import dao.BookDAO;
import domain.BookLoan;
import errors.ServiceException;

import java.sql.Connection;

public class ReturnService {
    private final LoanDAO loanDAO;
    private final BookDAO bookDAO;
    private final Connection connection;

    public ReturnService(Connection connection) {
        this.connection = connection;
        this.loanDAO = new LoanDAO(connection);
        this.bookDAO = new BookDAO(connection);
    }

    public boolean processReturn(int loanId) {
        try {
            BookLoan loan = loanDAO.getLoanById(loanId);
            System.out.println(loan);
            if (loan == null) {
                System.out.println("Loan not found");
                return false;
            }

            int bookId = loan.getBook_id();
            int quantity = loan.getBook_quantity();

            System.out.println(bookId + " " + quantity);

            boolean stockUpdated = bookDAO.increaseStock(bookId, quantity);
            if (!stockUpdated) {
                System.out.println("Failed to update book stock");
                return false;
            }

            boolean deleted = loanDAO.deleteLoan(loanId);
            if (!deleted) {
                System.out.println("Failed to delete loan after return");
                return false;
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error processing return: " + e.getMessage());
            throw new ServiceException("The return is missing");
        }
    }
}
