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

public class SearchLegalCustomerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //**receive data from html file
        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String customerNumber = request.getParameter("customerNumber");

        try {
            //**send data to logic layer
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.searchLogic(corporationName, financialCode, customerNumber);

            //**get output and print response to html file
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>Search Result For A Legal Customer</h3>" +
                    "<caption>Search Result:</caption>");
            out.print("<table width=50% border=2>");
            out.print("<tr>");
            for (int i = 6; i <= 10; i++) {
                out.print("<th>" + LegalCustomerCrud.getMetaDataResult().getColumnName(i) + "</th>");
            }
            for (int i = 0; i <= LegalCustomerCrud.getSearchResultArray().size(); i++) {
                out.println("<tr><td>" + LegalCustomerCrud.getSearchResultArray().get(i).getCustomerNumber()
                        + "</td><td>" + LegalCustomerCrud.getSearchResultArray().get(i).getCustomerType()
                        + "</td><td>" + LegalCustomerCrud.getSearchResultArray().get(i).getCorporationName()
                        + "</td><td>" + LegalCustomerCrud.getSearchResultArray().get(i).getFinancialCode()
                        + "</td><td>" + LegalCustomerCrud.getSearchResultArray().get(i).getRegisterDate()
                        + "<form action=\"/UpdateLegalCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + LegalCustomerCrud.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Update\"></td>"
                        + "</form>"
                        + "<form action=\"/DeleteLegalCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + LegalCustomerCrud.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Delete\"></td>"
                        + "</form>"
                        + "</td></tr>");
            }
            out.print("</table>");
            out.print("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}