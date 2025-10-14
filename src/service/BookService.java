package service;

import config.ConfigDB;
import dao.BookDAO;
import domain.LibraryBooks;
import errors.BadRequestException;

import java.sql.Connection;

public class BookService {
    private final BookDAO bookDAO;

    public BookService(Connection conn) {
        this.bookDAO = new BookDAO(conn);
    }

    public boolean createBook(String name, int stock, boolean status) {
        if (name == null || name.isEmpty()) {
            System.out.println("Book name is empty.");
            throw new BadRequestException("The name is empty");
        }
        if (stock < 0) {
            System.out.println("Stock cannot be negative.");
            return false;
        }

        LibraryBooks book = new LibraryBooks();
        book.setBook_name(name);
        book.setBook_stock(stock);
        book.setBook_status(status);

        boolean created = bookDAO.createBook(book);

        if (created) {
            System.out.println("Book created: " + name);
            return true;
        } else {
            System.out.println("Error creating book.");
            return false;
        }
    }

    public boolean ShowBooks(){
        return bookDAO.readBook();
    }
}
