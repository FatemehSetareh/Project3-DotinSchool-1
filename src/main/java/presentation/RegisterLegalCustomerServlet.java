package presentation;

import business.LegalLogic;
import persistence.crud.LegalCustomerCrud;

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

        try {
            //**send data to logic layer
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.checkRegisterLogic(corporationName, financialCode, registerDate);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //**get output and show result to user
        if (LegalLogic.getErrorMsg().equals("")) {
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>" + LegalCustomerCrud.getInsertionSuccess() + "</h3>" +
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
                    "<h2>" + LegalLogic.getErrorMsg() + "</h2>" +
                    "</body>" +
                    "</html>");
        }
    }


}