package dao.Interfaces;

import domain.LibraryUser;

public interface UserCRUD {
    boolean readUser();

    boolean deleteUser(int id);

    boolean createUser(LibraryUser user);

    LibraryUser getUserById(int id);

    boolean updateUser(LibraryUser user);
}
