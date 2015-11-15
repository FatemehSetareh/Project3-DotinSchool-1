package presentation;

import business.RealLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SearchRealCustomerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName;
        String lastName;
        String nationalCode;
        Integer customerNumber;

        if (!request.getParameter("firstName").equals("")) {
            firstName = request.getParameter("firstName");
        } else firstName = null;
        if (!request.getParameter("lastName").equals("")) {
            lastName = request.getParameter("lastName");
        } else lastName = null;
        if (!request.getParameter("nationalCode").equals("")) {
            nationalCode = request.getParameter("nationalCode");
        } else nationalCode = null;
        if (!request.getParameter("customerNumber").equals("")) {
            customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        } else customerNumber = null;

        System.out.println(firstName + "\n" + lastName + "\n"  + nationalCode + "\n" + customerNumber);
        try {
            RealLogic realLogic = new RealLogic();
            realLogic.searchLogic(firstName, lastName, nationalCode, customerNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
