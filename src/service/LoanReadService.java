package service;

import dao.LoanDAO;
import errors.NotFoundException;

import java.sql.Connection;

public class LoanReadService {

    private final LoanDAO loanDAO;

    public LoanReadService(Connection conn) {
        this.loanDAO = new LoanDAO(conn);
    }

    public boolean showLoansByUserId(int userId) {
        if (userId <= 0) {
            System.out.println("Invalid user ID.");
            throw new NotFoundException("The user id is invalid or may is not founded");
        }
        return loanDAO.readLoansByUserId(userId);
    }
}
