package business;

import persistence.crud.RealCustomerCrud;

import java.sql.SQLException;


public class RealLogic {
    private RealCustomerCrud realCustomerCrud;
    private static String errorMsg;

    public RealLogic() throws SQLException, ClassNotFoundException {
        realCustomerCrud = new RealCustomerCrud();
        errorMsg = "";
    }

    public void checkRegisterLogic(String firstName, String lastName, String fatherName, String nationalCode, String birthDate)
            throws SQLException, ClassNotFoundException {
        if (firstName.equals("")) {
            firstName = null;
            errorMsg += "First Name Field Is Empty" + "<br>";
        }
        if (lastName.equals("")) {
            lastName = null;
            errorMsg += "Last Name Field Is Empty" + "<br>";
        }
        if (fatherName.equals("")) {
            fatherName = null;
            errorMsg += "Father Name Field Is Empty" + "<br>";
        }
        if (nationalCode.equals("")) {
            nationalCode = null;
            errorMsg += "National Code Field Is Empty" + "<br>";
        }
        if (birthDate.equals("")) {
            birthDate = null;
            errorMsg += "Birth Date Field Is Empty" + "<br>";
        }
        if (firstName != null && lastName != null && fatherName != null && nationalCode != null && birthDate != null) {
            RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, nationalCode, birthDate, null, "1");

            if (realCustomerCrud.checkExistence(realCustomer)) {
                realCustomerCrud.insertToDatabase(realCustomer);

            } else {
                RealCustomerCrud.setInsertionSuccess("Sorry. This National Code Registered Before");
            }
        }
    }

    public void searchLogic(String firstName, String lastName, String nationalCode, String stringCustomerNumber) {
        Integer customerNumber;
        if (firstName.equals("")) {
            firstName = null;
        }
        if (lastName.equals("")) {
            lastName = null;
        }
        if (nationalCode.equals("")) {
            nationalCode = null;
        }
        if (stringCustomerNumber.equals("")) {
            customerNumber = null;
        } else {
            customerNumber = Integer.valueOf(stringCustomerNumber);
        }
        realCustomerCrud.searchDatabase(firstName, lastName, nationalCode, customerNumber);
    }

    public void deleteLogic(Integer customerNumber) {
        realCustomerCrud.deleteFromDatabase(customerNumber);
    }

    public void updateLogic(String firstName, String lastName, String fatherName, String nationalCode, String birthDate, Integer customerNumber) {
        realCustomerCrud.updateDatabase(firstName, lastName, fatherName, nationalCode, birthDate, customerNumber);
    }

    public static String getErrorMsg() {
        return errorMsg;
    }
}
