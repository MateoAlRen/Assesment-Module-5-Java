package controller.login;

import config.ConfigDB;
import service.CreateUser;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class SingInController {
    public SingInController() {
        String name = "";
        String email = "";
        String password = "";

        //
        boolean verifyName = true;
        while (verifyName) {
            name = JOptionPane.showInputDialog("Please, enter your name");

            if (name == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.");
                return;
            }

            if (EmptyValidator.isEmpty(name)) {
                JOptionPane.showMessageDialog(null, "Name can't be empty!");
                continue;
            }

            verifyName = false;
        }

        boolean verifyEmail = true;
        while (verifyEmail) {
            email = JOptionPane.showInputDialog("Please, enter your email");

            if (email == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.");
                return;
            }

            if (EmptyValidator.isEmpty(email)) {
                JOptionPane.showMessageDialog(null, "Email can't be empty!");
                continue;
            }
            verifyEmail = false;
        }

        boolean verifyPassword = true;
        while (verifyPassword) {
            password = JOptionPane.showInputDialog("Please, enter your password");

            if (password == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.");
                return;
            }

            if (EmptyValidator.isEmpty(password)) {
                JOptionPane.showMessageDialog(null, "Password can't be empty!");
                continue;
            }
            verifyPassword = false;
        }

        Connection conn = ConfigDB.openConnection();
        CreateUser.RegisterService registerService = new CreateUser.RegisterService(conn);

        boolean userCreated = registerService.register(name,email,password);

        if (userCreated){
            JOptionPane.showMessageDialog(null,"User created successfully!");
        } else{
            JOptionPane.showMessageDialog(null,"User has not been created");
        }

    }
}
