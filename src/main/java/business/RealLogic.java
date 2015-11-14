package business;

import persistence.crud.RealCustomerActions;
import presentation.RegisterRealCustomerServlet;

import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class RealLogic {

    RegisterRealCustomerServlet registerRealCustomerServlet;
    boolean flag = true;

    public void checkRegisterValidity(RealCustomer realCustomer)
            throws SQLException, ClassNotFoundException {
        RealCustomerActions realCustomerActions = new RealCustomerActions();
        if (flag == true) {
            realCustomerActions.insertToDatabase(realCustomer);
        } else
            registerRealCustomerServlet.printErrorReport();
    }
}
