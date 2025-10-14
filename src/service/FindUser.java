package service;

import dao.UserDAO;
import domain.LibraryUser;
import java.sql.Connection;

public class FindUser {

    public static class AuthService {
        private final UserDAO userDAO;

        public AuthService(Connection conn) {
            this.userDAO = new UserDAO(conn);
        }

        public LibraryUser login(String email, String password) {
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                System.out.println("Credentials are null.");
                return null;
            }

            LibraryUser user = userDAO.getUserByCredentials(email, password);
            if (user != null) {
                return user;
            } else {
                return null;
            }
        }
    }
}
