package presentation;

import business.RealLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class UpdateRealCustomerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //***these are lazem:))
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String nationalCode = request.getParameter("nationalCode");
        String birthDate = request.getParameter("birthDate");
        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));


        try {
            RealLogic realLogic = new RealLogic();
            realLogic.updateLogic(firstName, lastName, fatherName, nationalCode, birthDate, customerNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
