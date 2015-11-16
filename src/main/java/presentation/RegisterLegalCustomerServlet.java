package presentation;

import business.LegalCustomer;
import business.LegalLogic;
import persistence.crud.LegalCustomerActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterLegalCustomerServlet extends HttpServlet {
PrintWriter out;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        out = response.getWriter();

        //**get data from html file
        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String registerDate = request.getParameter("registerDate");
        LegalCustomer legalCustomer = new LegalCustomer(null, corporationName, financialCode, registerDate);

        try {
            //**send data to logic layer
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.checkRegisterLogic(legalCustomer);

            //**get output and show result to user
            out.print(LegalCustomerActions.getInsertionSuccess());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printErrorReport() {
        out.print("Error: This national code is in our database!");
    }

    public void printSuccessReport() {
        out.print("This customer successfully registered...");
    }
}