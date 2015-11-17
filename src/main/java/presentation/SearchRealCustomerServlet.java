package presentation;

import business.RealLogic;
import persistence.crud.RealCustomerActions;

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

        //**receive data from html file
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
            for (int i = 1; i <= 6; i++) {
                out.print("<th>" + RealCustomerActions.getMetaDataResult().getColumnName(i) + "</th>");
            }
            for (int i = 0; i <= RealCustomerActions.getSearchResultArray().size(); i++) {
                out.println("<tr><td>" + RealCustomerActions.getSearchResultArray().get(i).getFirstName()
                        + "</td><td>" + RealCustomerActions.getSearchResultArray().get(i).getLastName()
                        + "</td><td>" + RealCustomerActions.getSearchResultArray().get(i).getFatherName()
                        + "</td><td>" + RealCustomerActions.getSearchResultArray().get(i).getNationalCode()
                        + "</td><td>" + RealCustomerActions.getSearchResultArray().get(i).getBirthDate()
                        + "</td><td>" + RealCustomerActions.getSearchResultArray().get(i).getCustomerNumber()
                        + "<form action=\"/UpdateRealCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + RealCustomerActions.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
                        + "<td align='center'><input type='submit' value=\"Update\"></td>"
                        + "</form>"
                        + "<form action=\"/DeleteRealCustomerServlet\">"
                        + "</td><input type='hidden' name=\"customerNumber\" value=\"" + RealCustomerActions.getSearchResultArray().get(i).getCustomerNumber() + "\"><td>"
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