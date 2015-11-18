package presentation;

import business.LegalLogic;
import persistence.crud.LegalCustomerActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class UpdateLegalCustomerServlet extends HttpServlet {
    Integer customerNumber;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        System.out.println(customerNumber);

        out.print("<html>" +
                "<head>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                "</head>" +
                "" +
                "<body>" +
                "<h1>Dotin Internet Bank</h1>" +
                "" +
                "<h3>Legal Update Form</h3>" +
                "    <br>" +
                "    <br>" +
                "<form action=\"\\UpdateLegalCustomerServlet\" method=\"post\">" +
                "    <p>customerNumber is : " + customerNumber + " could not change...</p>" +
                "    <input type=\"text\" name=\"corporationName\" onfocus=\"if(this.value == 'Corporation Name') { this.value = ''; }\"" +
                "           value=\"Corporation Name\">" +
                "    <br>" +
                "    <input type=\"text\" name=\"financialCode\" onfocus=\"if(this.value == 'Financial Code') { this.value = ''; }\"" +
                "           value=\"Financial Code\">" +
                "    <br>" +
                "    <label>Register Date : </label>" +
                "    <br>" +
                "    <input type=\"date\" name=\"registerDate\" value=\"Register Date\">" +
                "    <br>" +
                "    <input type=\"submit\" value=\"Update\">" +
                "</form>" +
                "" +
                "" +
                "</body>" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String registerDate = request.getParameter("registerDate");

        try {
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.updateLogic(corporationName, financialCode, registerDate, customerNumber);

            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>" + LegalCustomerActions.getUpdatingSuccess() + "</h3>" +
                    "</body>" +
                    "</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
