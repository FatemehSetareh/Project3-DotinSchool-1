package business;

import persistence.crud.LegalCustomerActions;
import presentation.RegisterLegalCustomerServlet;

import java.sql.SQLException;


public class LegalLogic {
    boolean flag = true;
    LegalCustomerActions legalCustomerActions;
    RegisterLegalCustomerServlet registerLegalCustomerServlet;

    public LegalLogic() throws SQLException, ClassNotFoundException {
        legalCustomerActions = new LegalCustomerActions();
        registerLegalCustomerServlet = new RegisterLegalCustomerServlet();
    }

    public void checkRegisterLogic(LegalCustomer legalCustomer) throws SQLException, ClassNotFoundException {

        if (flag) {
            legalCustomerActions.insertToDatabase(legalCustomer);
        } ///else registerLegalCustomerServlet.printErrorReport();
    }

    public void searchLogic(String corporationName, String financialCode, Integer customerNumber) {
        //**just a middle wear between UI and DB
        legalCustomerActions.searchDatabase(corporationName, financialCode, customerNumber);
    }

    public void deleteLogic(Integer customerNumber){
        legalCustomerActions.deleteFromDatabase(customerNumber);
    }

    public void updateLogic(String corporationName, String financialCode, String registerDate, Integer customerNumber){
        legalCustomerActions.updateDatabase(corporationName, financialCode, registerDate, customerNumber);
    }
}
