package presentation;

import business.RealLogic;
import persistence.crud.RealCustomerCrud;

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

        try {
            //**send data to logic layer
            RealLogic realLogic = new RealLogic();
            realLogic.checkRegisterLogic(firstName, lastName, fatherName, nationalCode, birthDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //**get output and show result to user
        if (RealLogic.getErrorMsg().equals("")) {
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>" + RealCustomerCrud.getInsertionSuccess() + "</h3>" +
                    "</body>" +
                    "</html>");
        } else {
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>Sorry, Please Fill Form Correctly </h3>" +
                    "<br>" +
                    "<h2>" + RealLogic.getErrorMsg() + "</h2>" +
                    "</body>" +
                    "</html>");
        }


    }

}