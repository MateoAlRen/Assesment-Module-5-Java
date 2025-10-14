package controller.login;


import config.ConfigDB;
import domain.LibraryUser;
import service.FindUser;
import utils.EmptyValidator;
import utils.WindowCloser;
import view.MemberView;
import view.StaffView;

import javax.swing.*;
import java.sql.Connection;

public class LogInController {
    public LogInController(){
        String email = "";
        String password = "";

        boolean logIn = true;

        while (logIn){
            boolean emailCheck = true;
            while (emailCheck){
                email = JOptionPane.showInputDialog("Please, write your email");

                if (email == null){
                    JOptionPane.showMessageDialog(null,"Log In Cancelled");
                    return;
                }

                boolean validateEmpty = EmptyValidator.isEmpty(email);

                if (validateEmpty){
                    JOptionPane.showMessageDialog(null,"Email can't be empty!");
                    continue;
                }
                emailCheck = false;
            }
            boolean passwordCheck = true;
            while (passwordCheck){
                password = JOptionPane.showInputDialog("Write your password");

                if (password == null){
                    JOptionPane.showMessageDialog(null,"Log In Cancelled");
                    return;
                }

                boolean validateEmpty = EmptyValidator.isEmpty(password);
                if (validateEmpty){
                    JOptionPane.showMessageDialog(null,"Password can't be null!");
                    continue;
                }
                passwordCheck = false;
            }

            Connection conn = ConfigDB.openConnection();
            FindUser.AuthService authService = new FindUser.AuthService(conn);

            LibraryUser user = authService.login(email, password);

            if (user != null){
                if (user.isStaff_member()){
                    WindowCloser.closeAllFrames();
                    new StaffView(user);
                    return;
                } else if(!user.isStaff_member()){
                    WindowCloser.closeAllFrames();
                    new MemberView(user);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
                return;
            }
        }
    }
}
