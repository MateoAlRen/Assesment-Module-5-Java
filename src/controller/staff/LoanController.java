package controller.staff;

import config.ConfigDB;
import service.LoanService;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class LoanController {
    public LoanController() {
        Connection conn = ConfigDB.openConnection();
        LoanService loanService = new LoanService(conn);

        String userIdStr, bookIdStr, quantityStr;

        // Pide y valida User ID
        userIdStr = JOptionPane.showInputDialog("Enter User ID:");
        if (userIdStr == null || EmptyValidator.isEmpty(userIdStr)) {
            JOptionPane.showMessageDialog(null, "User ID cannot be empty");
            return;
        }

        // Pide y valida Book ID
        bookIdStr = JOptionPane.showInputDialog("Enter Book ID:");
        if (bookIdStr == null || EmptyValidator.isEmpty(bookIdStr)) {
            JOptionPane.showMessageDialog(null, "Book ID cannot be empty");
            return;
        }

        // Pide y valida cantidad
        quantityStr = JOptionPane.showInputDialog("Enter Quantity:");
        if (quantityStr == null || EmptyValidator.isEmpty(quantityStr)) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);
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
