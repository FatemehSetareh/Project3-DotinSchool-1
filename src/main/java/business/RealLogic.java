package business;

import persistence.crud.RealCustomerActions;

import java.sql.SQLException;


public class RealLogic {
    private boolean flag = true;
    private RealCustomerActions realCustomerActions;

    public RealLogic() throws SQLException, ClassNotFoundException {
        realCustomerActions = new RealCustomerActions();
    }

    public void checkRegisterLogic(RealCustomer realCustomer) throws SQLException, ClassNotFoundException {

        if (flag) {
            realCustomerActions.insertToDatabase(realCustomer);
        } //else
        //registerRealCustomerServlet.printErrorReport();
    }

    public void searchLogic(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        //**just a middle wear between UI and DB
        realCustomerActions.searchDatabase(firstName, lastName, nationalCode, customerNumber);
    }

    public void deleteLogic(Integer customerNumber){
        realCustomerActions.deleteFromDatabase(customerNumber);
    }

    public void updateLogic(String firstName, String lastName,String fatherName, String nationalCode,String birthDate, Integer customerNumber){
        realCustomerActions.updateDatabase(firstName, lastName, fatherName, nationalCode, birthDate, customerNumber);
    }

}
