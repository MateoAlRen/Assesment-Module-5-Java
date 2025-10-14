package controller.staff;

import config.ConfigDB;
import service.ReturnService;
import dao.LoanDAO;
import utils.EmptyValidator;

import javax.swing.*;
import java.sql.Connection;

public class ReturnController {

    public ReturnController() {
        Connection conn = ConfigDB.openConnection();
        LoanDAO loanDAO = new LoanDAO(conn);
        ReturnService returnService = new ReturnService(conn);

        // Pide y valida el User ID
        String userIdStr = JOptionPane.showInputDialog("Enter User ID to check loans:");
        if (userIdStr == null || EmptyValidator.isEmpty(userIdStr)) {
            JOptionPane.showMessageDialog(null, "User ID cannot be empty");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);

            // Mostrar préstamos de ese usuario
            boolean hasLoans = loanDAO.readLoansByUserId(userId);
            if (!hasLoans) return;

            String loanIdStr = JOptionPane.showInputDialog("Enter the Loan ID you want to return:");
            if (loanIdStr == null || EmptyValidator.isEmpty(loanIdStr)) {
                JOptionPane.showMessageDialog(null, "Loan ID cannot be empty");
                return;
            }

            int loanId = Integer.parseInt(loanIdStr);

            boolean success = returnService.processReturn(loanId);
            if (success) {
                JOptionPane.showMessageDialog(null, "Return processed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to process the return.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.");
        }
    }
}
