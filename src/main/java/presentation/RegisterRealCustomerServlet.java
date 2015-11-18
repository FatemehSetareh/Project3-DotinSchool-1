package presentation;

import business.RealCustomer;
import business.RealLogic;
import persistence.crud.RealCustomerActions;

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

        //**get data from html file
        String firstName;
        String lastName;
        String fatherName;
        String nationalCode;
        String birthDate;

        if (!request.getParameter("firstName").equals("")) {
            firstName = request.getParameter("firstName");
        } else firstName = null;

        if (!request.getParameter("lastName").equals("")) {
            lastName = request.getParameter("lastName");
        } else lastName = null;

        if (!request.getParameter("fatherName").equals("")) {
            fatherName = request.getParameter("fatherName");
        } else fatherName = null;

        if (!request.getParameter("nationalCode").equals("")) {
            nationalCode = request.getParameter("nationalCode");
        } else nationalCode = null;

        if (!request.getParameter("birthDate").equals("")) {
            birthDate = request.getParameter("birthDate");
        } else birthDate = null;

        if (firstName != null && lastName != null && fatherName != null && nationalCode != null && birthDate != null) {
            RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, nationalCode, birthDate, null);
            try {
                //**send data to logic layer
                RealLogic realLogic = new RealLogic();
                realLogic.checkRegisterLogic(realCustomer);

                //**get output and show result to user
                out.print("<html>" +
                        "<head>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                        "</head>" +
                        "" +
                        "<body>" +
                        "<h1>Dotin Internet Bank</h1>" +
                        "" +
                        "<h3>" + RealCustomerActions.getInsertionSuccess() + "</h3>" +
                        "</body>" +
                        "</html>");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>Sorry, Please Fill All Information </h3>" +
                    "</body>" +
                    "</html>");
        }


    }

}