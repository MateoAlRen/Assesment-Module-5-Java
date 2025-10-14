package view;

import controller.ShowBooksController;
import controller.staff.BookController;
import controller.staff.LoanController;
import controller.staff.ReturnController;
import controller.staff.UpdateBookController;
import domain.LibraryUser;
import utils.WindowCloser;

import javax.swing.*;

public class StaffView extends JFrame {
    public StaffView(LibraryUser user) {
        setTitle("LibraryNova");
        setSize(500, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Layout

        JLabel message = new JLabel("Welcome back, " + user.getUser_name());
        message.setBounds(160, 10, 500, 30);
        add(message);

        JButton LoanBtn = new JButton("Loan a book");
        LoanBtn.setBounds(160, 55, 150, 30);
        add(LoanBtn);

        JButton CreateBtn = new JButton("Create a book");
        CreateBtn.setBounds(160, 105, 150, 30);
        add(CreateBtn);

        JButton UpdateBtn = new JButton("Update a book");
        UpdateBtn.setBounds(160, 155, 150, 30);
        add(UpdateBtn);

        JButton ShowBtn = new JButton("Show Books");
        ShowBtn.setBounds(160,205,150,30);
        add(ShowBtn);

        JButton ReturnBtn = new JButton("Return Books");
        ReturnBtn.setBounds(160,255,150,30);
        add(ReturnBtn);


        JButton ExitBtn = new JButton("Exit");
        ExitBtn.setBounds(160,305,150,30);
        add(ExitBtn);

        LoanBtn.addActionListener(e -> {
            new LoanController();
        });

        CreateBtn.addActionListener(e -> {
           new BookController();
        });

        ShowBtn.addActionListener(e -> {
            new ShowBooksController();
        });

        UpdateBtn.addActionListener(e -> {
            new UpdateBookController();
        });

        ExitBtn.addActionListener(e -> {
            WindowCloser.closeAllFrames();
            new LoginView();
        });

        ReturnBtn.addActionListener(e -> {
            new ReturnController();
        });

        setVisible(true);
    }
}
