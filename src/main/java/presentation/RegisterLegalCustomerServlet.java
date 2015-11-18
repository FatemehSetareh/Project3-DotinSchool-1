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
        String corporationName;
        String financialCode;
        String registerDate;
        String errorMsg = "";

        if (!request.getParameter("corporationName").equals("")) {
            corporationName = request.getParameter("corporationName");
        } else {
            corporationName = null;
            errorMsg += "Corporation Name Field Is Empty" + "<br>";
        }

        if (!request.getParameter("financialCode").equals("")) {
            financialCode = request.getParameter("financialCode");
        } else {
            financialCode = null;
            errorMsg += "Financial Code Field Is Empty" + "<br>";
        }

        if (!request.getParameter("registerDate").equals("")) {
            registerDate = request.getParameter("registerDate");
        } else {
            registerDate = null;
            errorMsg += "Register Date Field Is Empty" + "<br>";
        }

        if (corporationName != null && financialCode != null && registerDate != null) {
            LegalCustomer legalCustomer = new LegalCustomer(null, corporationName, financialCode, registerDate);

            try {
                //**send data to logic layer
                LegalLogic legalLogic = new LegalLogic();
                legalLogic.checkRegisterLogic(legalCustomer);

                //**get output and show result to user
                out.print("<html>" +
                        "<head>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                        "</head>" +
                        "" +
                        "<body>" +
                        "<h1>Dotin Internet Bank</h1>" +
                        "" +
                        "<h3>" + LegalCustomerActions.getInsertionSuccess() + "</h3>" +
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
                    "<h3>Sorry, Please Fill Form Correctly </h3>" +
                    "<br>" +
                    "<h2>" + errorMsg + "</h2>" +
                    "</body>" +
                    "</html>");
        }
    }


}