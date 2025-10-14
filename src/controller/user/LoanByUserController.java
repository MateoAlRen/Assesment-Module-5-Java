package controller.user;

import config.ConfigDB;
import domain.LibraryUser;
import service.LoanService;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class LoanByUserController {
    public LoanByUserController(LibraryUser user) {
        Connection conn = ConfigDB.openConnection();
        LoanService loanService = new LoanService(conn);

        String bookIdStr, quantityStr;


        bookIdStr = JOptionPane.showInputDialog("Enter Book ID:");
        if (bookIdStr == null || EmptyValidator.isEmpty(bookIdStr)) {
            JOptionPane.showMessageDialog(null, "Book ID cannot be empty");
            return;
        }


        quantityStr = JOptionPane.showInputDialog("Enter Quantity:");
        if (quantityStr == null || EmptyValidator.isEmpty(quantityStr)) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty");
            return;
        }

        try {
            int userId = user.getUser_id();
            int bookId = Integer.parseInt(bookIdStr);
            int quantity = Integer.parseInt(quantityStr);

            boolean success = loanService.createLoan(userId, bookId, quantity);

            if (success) {
                JOptionPane.showMessageDialog(null, "Loan created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create loan. Check book availability or stock.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.");
        }
    }
}
