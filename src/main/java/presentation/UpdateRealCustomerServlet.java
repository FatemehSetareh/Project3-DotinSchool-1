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


public class UpdateRealCustomerServlet extends HttpServlet {
    Integer customerNumber;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        System.out.println(customerNumber);

        out.print("<html><head>" +
                "<head>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                "</head>" +
                "<body>" +
                "<h1>Dotin Internet Bank</h1>" +
                "<h3>Real Update Form</h3>" +
                "<form action=\"\\UpdateRealCustomerServlet\" method='post'>" +
                "   <p>customerNumber is : " + customerNumber + " could not change...</p>" +
                "    <input type=\"text\" name=\"firstName\" onfocus=\"if(this.value == 'First Name') { this.value = ''; }\"" +
                "           value=\"First Name\">" +
                "    <br>" +
                "    <input type=\"text\" name=\"lastName\" onfocus=\"if(this.value == 'Last Name') { this.value = ''; }\"" +
                "           value=\"Last Name\">" +
                "    <br>" +
                "    <input type=\"text\" name=\"fatherName\" onfocus=\"if(this.value == 'Father Name') { this.value = ''; }\"" +
                "           value=\"Father Name\">" +
                "    <br>" +
                "    <input type=\"text\" name=\"nationalCode\" onfocus=\"if(this.value == 'National Code') { this.value = ''; }\"" +
                "           value=\"National Code\">" +
                "    <br>" +
                "    Birthday Date :" +
                "    <br>" +
                "    <input type=\"date\" name=\"birthDate\" value=\"Birthday Date: \">" +
                "    <br>" +
                "    <input type=\"submit\" value=\"Update\">" +
                "</form>");

        out.print("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String nationalCode = request.getParameter("nationalCode");
        String birthDate = request.getParameter("birthDate");

        try {
            RealLogic realLogic = new RealLogic();
            realLogic.updateLogic(firstName, lastName, fatherName, nationalCode, birthDate, customerNumber);

            out.print("<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"Theme.css\" media=\"screen\" />" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>" + RealCustomerCrud.getUpdatingSuccess() + "</h3>" +
                    "</body>" +
                    "</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
