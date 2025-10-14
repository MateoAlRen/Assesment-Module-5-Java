package service;

import config.ConfigDB;
import dao.UserDAO;
import domain.LibraryUser;
import errors.BadRequestException;
import errors.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class CreateUser {

    public static class RegisterService {
        private final UserDAO userDAO;

        public RegisterService(Connection conn) {
            this.userDAO = new UserDAO(conn);
        }

        public boolean register(String name, String email, String password) {
            if (name == null || name.isEmpty() ||
                    email == null || email.isEmpty() ||
                    password == null || password.isEmpty()) {
                System.out.println("Some fields are empty or null.");
                throw new BadRequestException("You need to fill al the fields");
            }

            Connection conn = ConfigDB.openConnection();
            FindUser.AuthService authService = new FindUser.AuthService(conn);
            LibraryUser user = authService.login(email, password);

            if (user != null){
                JOptionPane.showMessageDialog(null,"The user already exists!");
                return false;
            }

            LibraryUser newUser = new LibraryUser();
            newUser.setUser_name(name);
            newUser.setUser_email(email);
            newUser.setUser_password(password);
            newUser.setStaff_member(false);

            boolean created = userDAO.createUser(newUser);

            if (created) {
                System.out.println("User created successfully: " + name);
                return true;
            } else {
                System.out.println("Error while creating user (maybe email already exists).");
                throw new ServiceException("There's an error in your petition");
            }
        }
    }
}
