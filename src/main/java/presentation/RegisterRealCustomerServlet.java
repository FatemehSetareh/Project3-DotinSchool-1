package presentation;

import business.RealCustomer;
import business.RealLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterRealCustomerServlet extends HttpServlet {
    PrintWriter out;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String nationalCode = request.getParameter("nationalCode");
        String birthDate = request.getParameter("birthDate");
        RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, nationalCode, birthDate);

        try {
            RealLogic realLogic = new RealLogic();
            realLogic.checkRegisterLogic(realCustomer);
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