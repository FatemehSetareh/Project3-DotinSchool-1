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

public class SearchLegalCustomerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName;
        String financialCode;
        Integer customerNumber;

        //**receive data from html file
        if (!request.getParameter("corporationName").equals("")) {
            corporationName = request.getParameter("corporationName");
        } else corporationName = null;

        if (!request.getParameter("financialCode").equals("")) {
            financialCode = request.getParameter("financialCode");
        } else financialCode = null;

        if (!request.getParameter("customerNumber").equals("")) {
            customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        } else customerNumber = null;

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
            for (int i = 6; i <= 9; i++) {
                out.print("<th>" + LegalCustomerActions.getMetaDataResult().getColumnName(i) + "</th>");
            }
            for (int i = 0; i <= LegalCustomerActions.getSearchResultArray().size(); i++) {
                out.println("<tr><td>" + LegalCustomerActions.getSearchResultArray().get(i).getCustomerNumber()
                        + "</td><td>" + LegalCustomerActions.getSearchResultArray().get(i).getCorporationName()
                        + "</td><td>" + LegalCustomerActions.getSearchResultArray().get(i).getFinancialCode()
                        + "</td><td>" + LegalCustomerActions.getSearchResultArray().get(i).getRegisterDate()
                        + "<form action=\"/UpdateLegalCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + LegalCustomerActions.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Update\"></td>"
                        + "</form>"
                        + "<form action=\"/DeleteLegalCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + LegalCustomerActions.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
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