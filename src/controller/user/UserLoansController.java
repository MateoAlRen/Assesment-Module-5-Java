package controller.user;

import config.ConfigDB;
import domain.LibraryUser;
import service.LoanReadService;

import java.sql.Connection;

public class UserLoansController {

    public UserLoansController(LibraryUser user) {
        Connection conn = ConfigDB.openConnection();
        LoanReadService service = new LoanReadService(conn);

        service.showLoansByUserId(user.getUser_id());

    }
}
