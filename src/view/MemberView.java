package view;

import controller.ShowBooksController;
import controller.user.LoanByUserController;
import controller.user.UserLoansController;
import domain.LibraryUser;
import utils.WindowCloser;

import javax.swing.*;

public class MemberView extends JFrame {
    public MemberView(LibraryUser user) {
        setTitle("LibraryNova");
        setSize(500, 290);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Layout

        JLabel message = new JLabel("Welcome back, " + user.getUser_name());
        message.setBounds(160, 10, 500, 30);
        add(message);

        JButton LoanBtn = new JButton("Loan a book");
        LoanBtn.setBounds(160, 55, 150, 30);
        add(LoanBtn);

        JButton ShowLoanBtn = new JButton("Show my loans");
        ShowLoanBtn.setBounds(160, 105, 150, 30);
        add(ShowLoanBtn);

        JButton ShowBookBtn = new JButton("Show books");
        ShowBookBtn.setBounds(160, 155, 150, 30);
        add(ShowBookBtn);

        JButton ExitBtn = new JButton("Exit");
        ExitBtn.setBounds(160,205,150,30);
        add(ExitBtn);


        LoanBtn.addActionListener(e -> {
            new LoanByUserController(user);
        });

        ShowLoanBtn.addActionListener(e -> {
            new UserLoansController(user);
        });

        ShowBookBtn.addActionListener(e -> {
            new ShowBooksController();
        });

        ExitBtn.addActionListener(e -> {
            WindowCloser.closeAllFrames();
            new LoginView();
        });

        setVisible(true);
    }
}
