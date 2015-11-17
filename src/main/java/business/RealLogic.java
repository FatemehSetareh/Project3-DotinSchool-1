package business;

import persistence.crud.RealCustomerActions;
import presentation.RegisterRealCustomerServlet;

import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class RealLogic {
    private boolean flag = true;
    private RealCustomerActions realCustomerActions;
    private RegisterRealCustomerServlet registerRealCustomerServlet;

    public RealLogic() throws SQLException, ClassNotFoundException {
        realCustomerActions = new RealCustomerActions();
        registerRealCustomerServlet = new RegisterRealCustomerServlet();
    }

    public void checkRegisterLogic(RealCustomer realCustomer) throws SQLException, ClassNotFoundException {

        if (flag == true) {
            realCustomerActions.insertToDatabase(realCustomer);
        } //else
        //registerRealCustomerServlet.printErrorReport();
    }

    public void searchLogic(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        //**just a middle wear between UI and DB
        realCustomerActions.searchDatabase(firstName, lastName, nationalCode, customerNumber);
    }

    public void updateLogic(String firstName, String lastName, String fatherName, String nationalCode, String birthDate, Integer customerNumber) {
        System.out.println("111111111111111111111111111111111111111111111111");
        realCustomerActions.updateDatabase(firstName, lastName, fatherName, nationalCode, birthDate, customerNumber);
    }

    public void deleteLogic(Integer customerNumber) {
        realCustomerActions.deleteFromDatabase(customerNumber);
    }


}
