package view;

import controller.login.LogInController;
import controller.login.SingInController;

import javax.swing.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("LibraryNova");
        setSize(500, 290);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Layout

        JLabel message = new JLabel("Welcome to LibraryNova");
        message.setBounds(160, 10, 500, 30);
        add(message);

        JButton LoginBtn = new JButton("Log In");
        LoginBtn.setBounds(160, 55, 150, 30);
        add(LoginBtn);

        JButton SignIn = new JButton("Sign In");
        SignIn.setBounds(160, 105, 150, 30);
        add(SignIn);

        JButton LoadData = new JButton("Load Data");
        LoadData.setBounds(160, 155, 150, 30);
        add(LoadData);

        JButton ExitBtn = new JButton("Exit");
        ExitBtn.setBounds(160,205,150,30);
        add(ExitBtn);

        ExitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"See you soon!");
            System.exit(0);
        });

        LoginBtn.addActionListener(e -> {
            new LogInController();
        });

        SignIn.addActionListener(e -> {
            new SingInController();
        });

        LoadData.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new CSVtoDBUploader().setVisible(true));
        });

        setVisible(true);
    }
}
