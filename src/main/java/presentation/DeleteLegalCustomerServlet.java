package presentation;

import persistence.crud.LegalCustomerActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class DeleteLegalCustomerServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        //PrintWriter out = response.getWriter();

        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));

        try {
            LegalCustomerActions legalCustomerActions = new LegalCustomerActions();
            legalCustomerActions.deleteFromDatabase(customerNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}