package domain;

public class LibraryBooks {
    private int book_id;
    private String book_name;
    private int book_stock;
    private boolean book_status;

    public LibraryBooks(){};

    public LibraryBooks(int book_id, String book_name, int book_stock, boolean book_status){
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_stock = book_stock;
        this.book_status = book_status;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_stock() {
        return book_stock;
    }

    public void setBook_stock(int book_stock) {
        this.book_stock = book_stock;
    }

    public boolean isBook_status() {
        return book_status;
    }

    public void setBook_status(boolean book_status) {
        this.book_status = book_status;
    }
}
