package business;

import persistence.crud.LegalCustomerCrud;

import java.sql.SQLException;


public class LegalLogic {
    LegalCustomerCrud legalCustomerCrud;
    private static String errorMsg;

    public LegalLogic() throws SQLException, ClassNotFoundException {
        legalCustomerCrud = new LegalCustomerCrud();
        errorMsg = "";
    }

    public void checkRegisterLogic(String corporationName, String financialCode, String registerDate) throws SQLException, ClassNotFoundException {
        if (corporationName.equals("")) {
            corporationName = null;
            errorMsg += "Corporation Name Field Is Empty" + "<br>";
        }
        if (financialCode.equals("")) {
            financialCode = null;
            errorMsg += "Financial Code Field Is Empty" + "<br>";
        }
        if (registerDate.equals("")) {
            registerDate = null;
            errorMsg += "Register Date Field Is Empty" + "<br>";
        }
        if (corporationName != null && financialCode != null && registerDate != null) {
            LegalCustomer legalCustomer = new LegalCustomer(null, "2", corporationName, financialCode, registerDate);

            if (legalCustomerCrud.checkExistence(legalCustomer)) {
                legalCustomerCrud.insertToDatabase(legalCustomer);

            } else {
                LegalCustomerCrud.setInsertionSuccess("Sorry. This Financial Code Registered Before");
            }
        }
    }

    public void searchLogic(String corporationName, String financialCode, String stringCustomerNumber) {
        Integer customerNumber;
        if (corporationName.equals("")) {
            corporationName = null;
        }
        if (financialCode.equals("")) {
            financialCode = null;
        }
        if (stringCustomerNumber.equals("")) {
            customerNumber = null;
        } else {
            customerNumber = Integer.valueOf(stringCustomerNumber);
        }
        legalCustomerCrud.searchDatabase(corporationName, financialCode, customerNumber);
    }

    public void deleteLogic(Integer customerNumber) {
        legalCustomerCrud.deleteFromDatabase(customerNumber);
    }

    public void updateLogic(String corporationName, String financialCode, String registerDate, Integer customerNumber) {
        legalCustomerCrud.updateDatabase(corporationName, financialCode, registerDate, customerNumber);
    }

    public static String getErrorMsg() {
        return errorMsg;
    }
}
