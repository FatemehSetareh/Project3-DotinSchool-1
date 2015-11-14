package business;

import persistence.crud.LegalCustomerActions;
import presentation.RegisterLegalCustomerServlet;

import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class LegalLogic {
    boolean flag = true;
    RegisterLegalCustomerServlet registerLegalCustomerServlet;

    public void checkRegisterValidity(LegalCustomer legalCustomer)
            throws SQLException, ClassNotFoundException {
        LegalCustomerActions legalCustomerActions = new LegalCustomerActions();
        if (flag == true) {
            legalCustomerActions.insertToDatabase(legalCustomer);
        } else registerLegalCustomerServlet.printErrorReport();
    }
}
