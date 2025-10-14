package dao.Interfaces;

import domain.LibraryBooks;

public interface BookCRUD {

    boolean createBook(LibraryBooks book);

    LibraryBooks getBookById(int id);

    boolean readBook();

    boolean updateBook(LibraryBooks book);

    boolean deleteBook(int id);
}
