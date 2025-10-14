package dao.Interfaces;

import domain.BookLoan;

public interface LoanCRUD {
    boolean createLoan(BookLoan loan);

    BookLoan getLoanById(int id);

    boolean readLoan();

    boolean updateLoan(BookLoan loan);

    boolean deleteLoan(int id);
}
