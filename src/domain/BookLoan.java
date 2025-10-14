package domain;

import java.sql.Timestamp;

public class BookLoan {
    private int loan_id;
    private int user_id;
    private int book_id;
    private int book_quantity;
    private Timestamp loan_date;

    public BookLoan(){};

    public BookLoan(int loan_id, int book_id, int user_id, int book_quantity, Timestamp loan_date){
        this.loan_id = loan_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_quantity = book_quantity;
        this.loan_date = loan_date;

    }

    public BookLoan(int loanId, int bookId, int userId, int bookQuantity, String s) {
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_quantity() {
        return book_quantity;
    }

    public void setBook_quantity(int book_quantity) {
        this.book_quantity = book_quantity;
    }

    public Timestamp getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Timestamp loan_date) {
        this.loan_date = loan_date;
    }
}
