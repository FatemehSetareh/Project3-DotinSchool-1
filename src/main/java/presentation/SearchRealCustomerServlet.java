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

public class SearchRealCustomerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //**receive data from html file
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nationalCode = request.getParameter("nationalCode");
        String customerNumber = request.getParameter("customerNumber");

        try {
            //**send data to logic layer
            RealLogic realLogic = new RealLogic();
            realLogic.searchLogic(firstName, lastName, nationalCode, customerNumber);

            //**get output and print response to html file
            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>Search Result For A Real Customer</h3>" +
                    "<caption>Search Result:</caption>" +
                    "<table width=50% border=2>");
            for (int i = 1; i <= 7; i++) {
                out.print("<th>" + RealCustomerCrud.getMetaDataResult().getColumnName(i) + "</th>");
            }
            for (int i = 0; i <= RealCustomerCrud.getSearchResultArray().size(); i++) {
                out.println("<tr><td>" + RealCustomerCrud.getSearchResultArray().get(i).getFirstName()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getLastName()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getFatherName()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getNationalCode()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getBirthDate()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getCustomerNumber()
                        + "</td><td>" + RealCustomerCrud.getSearchResultArray().get(i).getCustomerType()
                        + "<form action=\"/UpdateRealCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + RealCustomerCrud.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Update\"></td>"
                        + "</form>"
                        + "<form action=\"/DeleteRealCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + RealCustomerCrud.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Delete\"></td>"
                        + "</form>"
                        + "</td></tr>");

            }
            out.print("</table>"
                    + "</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}